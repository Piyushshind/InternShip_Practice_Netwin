import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import LoginForm from './pages/Auth/LoginForm';
import RegisterPage from './pages/Auth/RegisterPage';
import AdminDashboard from './pages/Admin/AdminDashboard';
import VisitorDashboard from './pages/Visitor/VisitorDashboard';
import Footer from './components/Layout/Footer';
import Header from './components/Layout/Header';
import NotFound from './pages/Auth/NotFound';

const App = () => {
  const [role, setRole] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const storedRole = sessionStorage.getItem('role');
    setRole(storedRole);
    setLoading(false);
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  const appStyles = {
    display: 'flex',
    flexDirection: 'column',
    minHeight: '100vh',
    width: '100%',
    overflowX: 'hidden',
  };

  const contentStyles = {
    flex: '1',
    width: '100%'
  };

  return (
    <Router>
      <div style={appStyles}>
        <Header role={role} />
        <div style={contentStyles}>
          <Routes>
            <Route path="/" element={<LoginForm setRole={setRole} />} />
            <Route path="/register" element={<RegisterPage />} />
            {role === 'Admin' ? (
              <Route path="/admin" element={<AdminDashboard />} />
            ) : role === 'Visitor' ? (
              <Route path="/visitor" element={<VisitorDashboard />} />
            ) : (
              <Route path="*" element={<Navigate to="/" />} />
            )}
            <Route path="*" element={<NotFound />} />
          </Routes>
        </div>
        <Footer />
      </div>
    </Router>
  );
};

export default App;
