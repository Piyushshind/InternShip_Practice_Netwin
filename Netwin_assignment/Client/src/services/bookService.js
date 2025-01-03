import api from './api';

export const fetchBooks = () => api.get('/books');
export const addBook = (book) => api.post('/books', book);
export const editBook = (bookId, book) => api.put(`/books/${bookId}`, book);
export const deleteBook = (bookId) => api.delete(`/books/${bookId}`);

export const fetchBooksDetails = async () => {
    try {
      const { data } = await api.get('/books');
      return data;

    } catch (err) {
      console.error('Failed to fetch books', err);
    }
  }