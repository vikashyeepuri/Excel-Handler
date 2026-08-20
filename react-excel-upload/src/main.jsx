import React, { useCallback, useEffect, useState } from 'react';
import { createRoot } from 'react-dom/client';
import './styles.css';

const API_URL = 'http://localhost:8080/api/excel';
const UPLOAD_URL = `${API_URL}/upload`;

function App() {
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState('');
  const [records, setRecords] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [isUploading, setIsUploading] = useState(false);

  const fetchRecords = useCallback(async () => {
    try {
      setIsLoading(true);
      const response = await fetch(API_URL);

      if (!response.ok) {
        throw new Error(`Could not load data: ${response.status}`);
      }

      const data = await response.json();
      setRecords(Array.isArray(data) ? data : []);
    } catch (error) {
      setMessage(`Could not load data: ${error.message}`);
    } finally {
      setIsLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchRecords();
  }, [fetchRecords]);

  const handleFileChange = (event) => {
    const selectedFile = event.target.files[0];

    if (!selectedFile) {
      setFile(null);
      return;
    }

    const allowedExtensions = ['.xlsx', '.xls'];
    const extension = selectedFile.name
      .substring(selectedFile.name.lastIndexOf('.'))
      .toLowerCase();

    if (!allowedExtensions.includes(extension)) {
      setFile(null);
      setMessage('Please select an Excel file (.xlsx or .xls).');
      event.target.value = '';
      return;
    }

    setFile(selectedFile);
    setMessage('');
  };

  const handleUpload = async () => {
    if (!file) {
      setMessage('Please select an Excel file first.');
      return;
    }

    const formData = new FormData();
    formData.append('file', file);

    try {
      setIsUploading(true);
      setMessage('Uploading...');

      const response = await fetch(UPLOAD_URL, {
        method: 'POST',
        body: formData
      });

      if (!response.ok) {
        throw new Error(`Upload failed: ${response.status}`);
      }

      setMessage('File uploaded successfully.');
      setFile(null);
      await fetchRecords();
    } catch (error) {
      setMessage(`Upload failed: ${error.message}`);
    } finally {
      setIsUploading(false);
    }
  };

  return (
    <main className="container">
      <section className="upload-card">
        <h1>Excel File Upload</h1>

        <input
          type="file"
          accept=".xlsx,.xls"
          onChange={handleFileChange}
        />

        {file && <p className="file-name">{file.name}</p>}

        <button onClick={handleUpload} disabled={!file || isUploading}>
          {isUploading ? 'Uploading...' : 'Upload'}
        </button>

        {message && <p className="message">{message}</p>}
      </section>

      <section className="table-card" aria-labelledby="records-heading">
        <h2 id="records-heading">Uploaded data</h2>

        {isLoading ? (
          <p className="table-status">Loading data...</p>
        ) : records.length === 0 ? (
          <p className="table-status">No records found.</p>
        ) : (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Age</th>
                  <th>City</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
                {records.map((record) => (
                  <tr key={record.id}>
                    <td>{record.id}</td>
                    <td>{record.name}</td>
                    <td>{record.age}</td>
                    <td>{record.city}</td>
                    <td>{record.status}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </section>
    </main>
  );
}

createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
