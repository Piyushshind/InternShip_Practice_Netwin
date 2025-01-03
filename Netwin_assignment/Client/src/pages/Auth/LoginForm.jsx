import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../../services/api';

const LoginForm = ({ setRole }) => {
  const [form, setForm] = useState({ username: '', password: '' });
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const { data } = await api.post('/users/login', form);

      sessionStorage.setItem('authToken', data.token || "");
      sessionStorage.setItem('role', data.role || "Visitor");
      sessionStorage.setItem('userId', data.userId || 1);

      setRole(data.role);

      navigate(data.role === 'Admin' ? '/admin' : '/visitor');
    } catch (err) {
      console.error('Login failed', err);
      alert('Invalid username or password. Please try again.');
      setForm({ username: '', password: '' });
    }
  };

  const styles = {
    form: {
      maxWidth: '400px',
      margin: '50px auto',
      padding: '20px',
      border: 'none',
      borderRadius: '10px',
      backgroundColor: '#f4f6f8',
      boxShadow: '0 2px 5px rgba(0,0,0,0.1)',
      fontFamily: 'Arial, sans-serif'
    },
    formControl: {
      marginBottom: '20px',
    },
    label: {
      display: 'block',
      marginBottom: '5px',
      fontWeight: 'bold',
      color: '#333'
    },
    input: {
      width: '100%',
      padding: '10px',
      borderRadius: '5px',
      border: '1px solid #ccc',
      boxSizing: 'border-box',
      fontSize: '16px',
    },
    button: {
      width: '100%',
      padding: '15px',
      border: 'none',
      borderRadius: '5px',
      backgroundColor: '#007bff',
      color: '#fff',
      fontSize: '16px',
      cursor: 'pointer',
      transition: 'background-color 0.3s',
      textAlign: 'center'
    },
    buttonHover: {
      backgroundColor: '#0056b3'
    }
  };

  return (
    <form onSubmit={handleSubmit} style={styles.form}>
      <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Login</h2>
      <div style={styles.formControl}>
        <label style={styles.label}>Username</label>
        <input
          type="text"
          placeholder="Username"
          value={form.username}
          onChange={(e) => setForm({ ...form, username: e.target.value })}
          required
          style={styles.input}
        />
      </div>
      <div style={styles.formControl}>
        <label style={styles.label}>Password</label>
        <input
          type="password"
          placeholder="Password"
          value={form.password}
          onChange={(e) => setForm({ ...form, password: e.target.value })}
          required
          style={styles.input}
        />
      </div>
      <button
        type="submit"
        style={styles.button}
        onMouseEnter={(e) => (e.target.style.backgroundColor = styles.buttonHover.backgroundColor)}
        onMouseLeave={(e) => (e.target.style.backgroundColor = styles.button.backgroundColor)}
      >
        Login
      </button>
    </form>
  );
};

export default LoginForm;
