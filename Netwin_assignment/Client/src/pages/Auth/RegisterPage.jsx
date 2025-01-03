import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const RegisterPage = () => {
    const [form, setForm] = useState({ username: '', password: '', role: 'Visitor', userEmail: '' });
    const navigate = useNavigate();
    const [errors, setErrors] = useState({});

    const validateForm = () => {
        const errors = {};

        // Username validation: alphanumeric, length between 3-20 characters
        const usernameRegex = /^[a-zA-Z0-9]{3,20}$/;
        if (!usernameRegex.test(form.username)) {
            errors.username = 'Username must be alphanumeric and between 3-20 characters.';
        }

        // Email validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(form.userEmail)) {
            errors.userEmail = 'Please enter a valid email address.';
        }

        // Password validation: Minimum 6 characters
        if (form.password.length < 6) {
            errors.password = 'Password must be at least 6 characters long.';
        }

        setErrors(errors);

        // Return true if no errors
        return Object.keys(errors).length === 0;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!validateForm()) {
            return;
        }

        try {
            await axios.post('http://localhost:8080/api/users/register', form);
            alert("🎉 Registration Successful! 🎉  You can now log in ")
            navigate('/');
        } catch (err) {
            console.error('Registration failed', err);
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
        select: {
            width: '100%',
            padding: '10px',
            borderRadius: '5px',
            border: '1px solid #ccc',
            fontSize: '16px',
            boxSizing: 'border-box',
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
        },
        error: {
            color: 'red',
            fontSize: '14px',
            marginTop: '5px',
        }
    };

    return (
        <form onSubmit={handleSubmit} style={styles.form}>
            <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Register</h2>
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
                {errors.username && <p style={styles.error}>{errors.username}</p>}
            </div>
            <div style={styles.formControl}>
                <label style={styles.label}>Email</label>
                <input
                    type="email"
                    placeholder="Email"
                    value={form.userEmail}
                    onChange={(e) => setForm({ ...form, userEmail: e.target.value })}
                    required
                    style={styles.input}
                />
                {errors.userEmail && <p style={styles.error}>{errors.userEmail}</p>}
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
                {errors.password && <p style={styles.error}>{errors.password}</p>}
            </div>
            <div style={styles.formControl}>
                <label style={styles.label}>Role</label>
                <select
                    value={form.role}
                    onChange={(e) => setForm({ ...form, role: e.target.value })}
                    style={styles.select}
                >
                    <option value="Visitor">Visitor</option>
                    <option value="Admin">Admin</option>
                </select>
            </div>
            <button
                type="submit"
                style={styles.button}
                onMouseEnter={(e) => (e.target.style.backgroundColor = styles.buttonHover.backgroundColor)}
                onMouseLeave={(e) => (e.target.style.backgroundColor = styles.button.backgroundColor)}
            >
                Register
            </button>
        </form>
    );
};

export default RegisterPage;
