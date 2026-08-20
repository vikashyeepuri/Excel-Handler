import { useState, useRef } from 'react';
import type { ChangeEvent, FormEvent } from 'react';
import toast from 'react-hot-toast';
import * as XLSX from 'xlsx';

const ExcelUploader = () => {
  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  const [isUploading, setIsUploading] = useState<boolean>(false);
  
  // Table & Pagination State
  const [excelData, setExcelData] = useState<any[][]>([]);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const rowsPerPage = 5; 

  const fileInputRef = useRef<HTMLInputElement>(null);

  // Parse Excel file and extract data
  const readExcelFile = (file: File) => {
    const reader = new FileReader();
    reader.onload = (e) => {
      try {
        const data = e.target?.result;
        const workbook = XLSX.read(data, { type: 'array' });
        const firstSheetName = workbook.SheetNames[0];
        const worksheet = workbook.Sheets[firstSheetName];
        
        const jsonData = XLSX.utils.sheet_to_json<any[]>(worksheet, { header: 1 });
        setExcelData(jsonData);
        setCurrentPage(1); 
      } catch (error) {
        console.error("Error parsing Excel:", error);
        toast.error("Failed to read the Excel file.");
      }
    };
    reader.readAsArrayBuffer(file);
  };

  const handleFileChange = (event: ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;
    if (files && files.length > 0) {
      const file = files[0];
      setSelectedFile(file);
      readExcelFile(file); 
    }
  };

  const handleRemoveFile = () => {
    setSelectedFile(null);
    setExcelData([]); 
    if (fileInputRef.current) {
      fileInputRef.current.value = '';
    }
  };

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (!selectedFile) {
      toast.error('Please select an Excel file first.');
      return;
    }

    const formData = new FormData();
    formData.append('file', selectedFile); 
    formData.append('traceId', crypto.randomUUID().substring(0, 8));

    const toastId = toast.loading('Uploading...');

    try {
      setIsUploading(true);
      const response = await fetch('http://192.168.1.56:8090/api/excel/upload', {
        method: 'POST',
        body: formData,
      });

      if (response.ok) {
        toast.success('File uploaded successfully!', { id: toastId });
        // handleRemoveFile(); // Uncomment if you want to clear table on success
      } else {
        toast.error(`Upload failed. Server responded with status: ${response.status}`, { id: toastId });
      }
    } catch (error) {
      console.error('Upload Error:', error);
      toast.error('Network error or server is unreachable.', { id: toastId });
    } finally {
      setIsUploading(false);
    }
  };

  // Pagination Logic
  const headers = excelData.length > 0 ? excelData[0] : [];
  const rows = excelData.length > 1 ? excelData.slice(1) : [];
  const totalPages = Math.ceil(rows.length / rowsPerPage);
  
  const currentRows = rows.slice(
    (currentPage - 1) * rowsPerPage, 
    currentPage * rowsPerPage
  );

  return (
    <div className="w-full max-w-5xl mx-auto my-12 p-8 bg-orange-50 rounded-xl shadow-lg border border-orange-200 font-sans flex flex-col items-center">
      
      {/* Upload Section */}
      <div className="w-full max-w-md mx-auto mb-10">
        <h2 className="text-3xl font-bold text-orange-900 mb-8 text-center tracking-tight">
          Upload Excel Data
        </h2>
        
        <form onSubmit={handleSubmit} className="flex flex-col gap-6">
          <div className="flex items-center justify-between gap-4">
            <label className="block flex-1">
              <span className="sr-only">Choose Excel File</span>
              <input
                type="file"
                accept=".xlsx, .xls, .csv"
                onChange={handleFileChange}
                disabled={isUploading}
                ref={fileInputRef}
                className="block w-full text-sm text-orange-800
                  file:mr-4 file:py-2.5 file:px-4
                  file:rounded-md file:border-0
                  file:text-sm file:font-semibold
                  file:bg-orange-200 file:text-orange-900
                  hover:file:bg-orange-300
                  focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent
                  disabled:opacity-50 disabled:cursor-not-allowed transition-colors"
              />
            </label>

            {selectedFile && (
              <button
                type="button"
                onClick={handleRemoveFile}
                disabled={isUploading}
                className="px-4 py-2.5 text-sm font-semibold text-blue-700 bg-blue-100 rounded-md hover:bg-blue-200 focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:opacity-50 disabled:cursor-not-allowed transition-colors"
              >
                Clear
              </button>
            )}
          </div>
          
          <button 
            type="submit" 
            disabled={!selectedFile || isUploading}
            className="w-full py-3 px-4 bg-orange-600 text-white font-semibold rounded-md shadow-sm 
                       hover:bg-orange-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 focus:ring-offset-orange-50
                       disabled:bg-orange-300 disabled:cursor-not-allowed transition-colors duration-200 text-lg"
          >
            {isUploading ? 'Submitting...' : 'Submit File'}
          </button>
        </form>
      </div>

      {/* Table Preview Section */}
      {excelData.length > 0 && (
        <div className="w-full border-t border-orange-200 pt-8">
          <h3 className="text-xl font-semibold text-orange-900 mb-4 text-center">
            Data Preview <span className="text-base font-medium text-orange-700 ml-2">({rows.length} rows found)</span>
          </h3>
          
          {/* Constant Size Table Container (h-80) */}
          <div className="w-full h-80 overflow-auto rounded-lg border border-orange-200 shadow-sm bg-white">
            <table className="w-full text-sm text-left relative">
              <thead className="bg-orange-200 text-orange-900 sticky top-0 z-10 shadow-sm">
                <tr>
                  {headers.map((header, index) => (
                    <th key={index} className="px-6 py-4 font-bold whitespace-nowrap">
                      {header || `Column ${index + 1}`}
                    </th>
                  ))}
                </tr>
              </thead>
              <tbody className="divide-y divide-orange-100">
                {currentRows.length > 0 ? (
                  currentRows.map((row, rowIndex) => (
                    <tr key={rowIndex} className="hover:bg-orange-50 transition-colors">
                      {headers.map((_, colIndex) => (
                        <td key={colIndex} className="px-6 py-3 text-gray-700 whitespace-nowrap">
                          {row[colIndex] !== undefined ? String(row[colIndex]) : '-'}
                        </td>
                      ))}
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={headers.length} className="px-6 py-8 text-center text-gray-500 italic">
                      No data available
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>

          {/* Pagination Controls */}
          {totalPages > 1 && (
            <div className="flex items-center justify-between mt-6 px-2">
              <span className="text-sm font-medium text-orange-800">
                Page <span className="font-bold text-orange-900">{currentPage}</span> of <span className="font-bold text-orange-900">{totalPages}</span>
              </span>
              
              <div className="flex gap-3">
                <button
                  type="button"
                  onClick={() => setCurrentPage(prev => Math.max(prev - 1, 1))}
                  disabled={currentPage === 1}
                  className="px-4 py-2 text-sm font-semibold text-blue-700 bg-blue-100 rounded-md hover:bg-blue-200 disabled:opacity-50 disabled:cursor-not-allowed transition-colors focus:ring-2 focus:ring-blue-500"
                >
                  Previous
                </button>
                <button
                  type="button"
                  onClick={() => setCurrentPage(prev => Math.min(prev + 1, totalPages))}
                  disabled={currentPage === totalPages}
                  className="px-4 py-2 text-sm font-semibold text-blue-700 bg-blue-100 rounded-md hover:bg-blue-200 disabled:opacity-50 disabled:cursor-not-allowed transition-colors focus:ring-2 focus:ring-blue-500"
                >
                  Next
                </button>
              </div>
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default ExcelUploader;