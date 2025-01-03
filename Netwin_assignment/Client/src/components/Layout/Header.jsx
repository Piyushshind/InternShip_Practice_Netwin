import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const Header = ({ role }) => {
  const [userRole] = useState(role || "N");
  console.log(role);

  const styles = {
    header: {
      backgroundColor: '#ffffff', // White background
      padding: '20px',
      boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
      position: 'fixed',
      top: '0',
      width: '100%',
      zIndex: '1000',
      marginBottom: '20px', // Added margin-bottom for spacing
    },
    nav: {
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center',
      maxWidth: '1200px',
      margin: '0 auto',
    },
    navLink: {
      textDecoration: 'none',
      color: '#007bff', // Light sky blue text color
      margin: '0 15px',
      fontSize: '18px',
      transition: 'color 0.3s',
      fontFamily: 'Arial, sans-serif',
    },
    navLinkHover: {
      color: '#0056b3', // Darker blue for hover effect
    },
    role: {
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      fontWeight: 'bold',
      color: '#ffffff', // White text color for better contrast
      backgroundColor: '#007bff', // Light sky blue background color
      width: '40px',
      height: '40px',
      borderRadius: '50%',
      textTransform: 'uppercase',
    }
  };

  return (
    <header style={styles.header}>
      <nav style={styles.nav}>
        <Link 
          style={styles.navLink} 
          to="/" 
          onMouseEnter={(e) => e.target.style.color = styles.navLinkHover.color}
          onMouseLeave={(e) => e.target.style.color = styles.navLink.color}
        >
          Home
        </Link>
        <Link 
          style={styles.navLink} 
          to="/"
          onMouseEnter={(e) => e.target.style.color = styles.navLinkHover.color}
          onMouseLeave={(e) => e.target.style.color = styles.navLink.color}
        >
          Login
        </Link>
        <Link 
          style={styles.navLink} 
          to="/register"
          onMouseEnter={(e) => e.target.style.color = styles.navLinkHover.color}
          onMouseLeave={(e) => e.target.style.color = styles.navLink.color}
        >
          Register
        </Link>
        {userRole && (
          <span style={styles.role}>
            {userRole.charAt(0)}
          </span>
        )}
      </nav>
    </header>
  );
};

export default Header;
