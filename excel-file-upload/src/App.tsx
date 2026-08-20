// App.tsx
import { Toaster } from 'react-hot-toast';
import ExcelUploader from './components/ExcelUploader';

function App() {
  return (
    <div>
      {/* Global Toaster placed at the root */}
      <Toaster position="top-center" reverseOrder={false} />
      
      <main>
        <ExcelUploader />
      </main>
    </div>
  );
}

export default App;