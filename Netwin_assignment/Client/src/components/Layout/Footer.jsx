import React from 'react';

const Footer = () => {
  const styles = {
    footer: {
      backgroundColor: '#ffffff',
      padding: '20px', boxShadow: '0 -2px 4px rgba(0, 0, 0, 0.1)',
      width: '100%', textAlign: 'center',
      marginTop: 'auto',
    },
    link: {
      textDecoration: 'none',
      color: '#007bff',
      margin: '0 15px',
      fontSize: '16px',
      transition: 'color 0.3s',
      fontFamily: 'Arial, sans-serif',
    },
    linkHover: {
      color: '#0056b3',
    },
    copy: {
      marginTop: '10px',
      color: '#343a40',
      fontSize: '14px',
    }
  };

  return (
    <footer style={styles.footer} >
      <div>
        <a
          href="https://netwin.in/"
          style={styles.link}
          onMouseEnter={(e) => e.target.style.color = styles.linkHover.color}
          onMouseLeave={(e) => e.target.style.color = styles.link.color}
        >
          Netwin
        </a>
        <a
          href="#"
          style={styles.link}
          onMouseEnter={(e) => e.target.style.color = styles.linkHover.color}
          onMouseLeave={(e) => e.target.style.color = styles.link.color}
        >
          About Us
        </a>
        <a
          href="#"
          style={styles.link}
          onMouseEnter={(e) => e.target.style.color = styles.linkHover.color}
          onMouseLeave={(e) => e.target.style.color = styles.link.color}
        >
          Contact Us
        </a>
      </div>
      <div style={styles.copy}>
        © 2024 Book Library Management System. All Rights Reserved.
      </div>
    </footer >
  );
};

export default Footer;
