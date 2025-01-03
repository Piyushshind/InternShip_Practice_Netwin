import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import { bookState } from '../../recoil/bookState';
import api from '../../services/api';

const BookFilters = () => {
  const [filters, setFilters] = useState({ genre: '', author: '', year: '' });
  const [books, setBooks] = useRecoilState(bookState);

  const applyFilters = async () => {
    const param = new URLSearchParams();
    if (filters.genre) param.append('genreName', filters.genre);
    if (filters.author) param.append('authorName', filters.author);
    if (filters.year) param.append('publicationYear', filters.year);

    try {
      console.log('Applying filters...');
      const { data } = await api.get('/books/filter', { params: param });
      setBooks(data);
    } catch (err) {
      console.error('Failed to fetch filtered books:', err);
    }
  };

  const styles = {
    container: {
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      justifyContent: 'center',
      backgroundColor: '#f9f9f9',
      textAlign: 'center',
      padding: '20px',
      margin: '20px 0',
    },
    form: {
      display: 'flex',
      justifyContent: 'space-around',
      width: '100%',
      maxWidth: '800px',
    },
    label: {
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      margin: '0 10px',
    },
    input: {
      padding: '8px',
      margin: '5px 0',
      border: '1px solid #ddd',
      borderRadius: '4px',
      width: '150px',
    },
    button: {
      padding: '8px 16px',
      border: 'none',
      cursor: 'pointer',
      backgroundColor: '#007bff',
      color: '#fff',
      borderRadius: '4px',
      transition: 'background-color 0.3s',
      alignSelf: 'center',
    },
    buttonHover: {
      backgroundColor: '#0056b3',
    },
  };

  return (
    <div style={styles.container}>
      <h3>Filter Books</h3>
      <div style={styles.form}>
        <div style={styles.label}>
          <label>Genre</label>
          <input
            type="text"
            placeholder="Genre"
            value={filters.genre}
            onChange={(e) => setFilters({ ...filters, genre: e.target.value })}
            style={styles.input}
          />
        </div>
        <div style={styles.label}>
          <label>Author</label>
          <input
            type="text"
            placeholder="Author"
            value={filters.author}
            onChange={(e) => setFilters({ ...filters, author: e.target.value })}
            style={styles.input}
          />
        </div>
        <div style={styles.label}>
          <label>Publication Year</label>
          <input
            type="number"
            placeholder="Publication Year"
            value={filters.year}
            onChange={(e) => setFilters({ ...filters, year: e.target.value })}
            style={styles.input}
          />
        </div>
      </div>
      <button
        onClick={applyFilters}
        style={styles.button}
        onMouseEnter={(e) => (e.target.style.backgroundColor = styles.buttonHover.backgroundColor)}
        onMouseLeave={(e) => (e.target.style.backgroundColor = styles.button.backgroundColor)}
      >
        Apply Filters
      </button>
    </div>
  );
};

export default BookFilters;
