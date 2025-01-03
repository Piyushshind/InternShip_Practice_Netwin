import React, { useEffect, useState } from 'react';
import { useRecoilState } from 'recoil';
import { bookState } from '../../recoil/bookState';
import api from '../../services/api';

const BookItem = ({ setFormData }) => {
  const [books, setBooks] = useRecoilState(bookState);
  const [currentPage, setCurrentPage] = useState(1);
  const [reviewContent, setReviewContent] = useState('');
  const [rating, setRating] = useState(0);
  const [selectedBookId, setSelectedBookId] = useState(null);
  const [storedUserId] = useState(sessionStorage.getItem('userId') || 1);
  const [reviews, setReviews] = useState({});
  const booksPerPage = 10;
  const fetchBooksDetails = async () => {
    try {
      const { data } = await api.get('/books');
      setBooks(data);
    } catch (err) {
      console.error('Failed to fetch books', err);
    }
  };
  const fetchReviews = async (bookId) => {
    try {
      const { data } = await api.get(`/reviews/book/${bookId}`);
      setReviews((prevReviews) => ({ ...prevReviews, [bookId]: data }));
    } catch (err) { console.error('Failed to fetch reviews', err); }
  };
  useEffect(() => {
    fetchBooksDetails();
  }, []);
  const handleReviewSubmit = async (bookId) => {
    if (!reviewContent || rating <= 0 || rating > 5) {
      alert('Please enter valid review content and rating (1-5).');
      return;
    }
    const reviewData = {
      bookId: bookId,
      userId: storedUserId,
      reviewContent: reviewContent,
      rating: rating
    };
    try {
      await api.post('/reviews', reviewData);
      alert('Review submitted successfully!');
      setReviewContent('');
      setRating(0);
      fetchReviews(bookId); // Fetch reviews again after adding a new review
      setSelectedBookId(null); // Hide the review input fields
    } catch (err) {
      console.error('Failed to submit review', err);
      alert('Failed to submit review. Please try again.');
    }
  };
  const handleClick = (pageNumber) => {
    setCurrentPage(pageNumber);
  };
  const indexOfLastBook = currentPage * booksPerPage;
  const indexOfFirstBook = indexOfLastBook - booksPerPage;
  const currentBooks = books.slice(indexOfFirstBook, indexOfLastBook);
  const handleAddReviewClick = (bookId) => {
    setSelectedBookId(bookId);
    setReviewContent('');
    setRating(0);
  };

  const handleEdit = (book) => {
    setFormData({
      bookId: book.bookId,
      title: book.title || "",
      authorName: book.author.authorName || "",
      genreName: book.genre.genreName || "",
      publicationYear: book.publicationYear || 0,
      quantityAvailable: book.quantity || 0,
    });
  };
  const handleDelete = async (id) => {
    try {
      await api.delete(`/books/${id}`);
      setBooks((prevBooks) => prevBooks.filter((book) => book.bookId !== id));
      alert('Book deleted successfully');
    } catch (error) {
      console.error('Error deleting book:', error);
      alert('Failed to delete book. Please try again.');
    }
  };
  const styles = {
    container: { display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', backgroundColor: '#f9f9f9', textAlign: 'center', padding: '20px' },
    table: { width: '100%', borderCollapse: 'collapse', marginTop: '20px', },
    th: { padding: '8px', border: '1px solid #ddd', backgroundColor: '#f4f4f4' },
    td: { padding: '8px', border: '1px solid #ddd' },
    pagination: { display: 'flex', justifyContent: 'center', margin: '20px 0' },
    pageButton: { margin: '0 5px', padding: '8px 16px', border: 'none', cursor: 'pointer', backgroundColor: '#007bff', color: '#fff', borderRadius: '4px', transition: 'background-color 0.3s' },
    pageButtonHover: { backgroundColor: '#0056b3' },
    reviewInput: { width: '100%', padding: '8px', margin: '8px 0', boxSizing: 'border-box' },
    reviewButton: { margin: '8px 0', padding: '8px 16px', border: 'none', cursor: 'pointer', backgroundColor: '#28a745', color: '#fff', borderRadius: '4px', transition: 'background-color 0.3s' }, reviewButtonHover: { backgroundColor: '#218838' }
  };
  return (
    <div style={styles.container}>
      <h2>Books</h2>
      <table style={styles.table}>
        <thead>
          <tr>
            <th style={styles.th}>Title</th>
            <th style={styles.th}>Author</th>
            <th style={styles.th}>Genre</th>
            <th style={styles.th}>Publication Year</th>
            <th style={styles.th}>Available</th>
            <th style={styles.th}>Quantity</th>
            <th style={styles.th}>Reviews</th>
            <th style={styles.th}>Actions</th>
          </tr>
        </thead>
        <tbody>
          {currentBooks.map((book) => (
            <tr key={book.bookId}>
              <td style={styles.td}>{book.title || "N/A"}</td>
              <td style={styles.td}>{book.author.authorName || "N/A"}</td>
              <td style={styles.td}>{book.genre.genreName || "N/A"}</td>
              <td style={styles.td}>{book.publicationYear || "N/A"}</td>
              <td style={styles.td}>{book.isAvailable ? "Yes" : "No"}</td>
              <td style={styles.td}>{book.quantity || 0}</td>
              <td style={styles.td}>
                {reviews[book.bookId] && reviews[book.bookId].length > 0 ? (
                  reviews[book.bookId].map((review) => (
                    <div key={review.reviewId}>
                      <p>{review.reviewContent} - Rating: {review.rating}</p>
                    </div>
                  ))
                ) : (
                  <button
                    style={styles.reviewButton}
                    onMouseEnter={(e) => e.target.style.backgroundColor = styles.reviewButtonHover.backgroundColor}
                    onMouseLeave={(e) => e.target.style.backgroundColor = styles.reviewButton.backgroundColor}
                    onClick={() => handleAddReviewClick(book.bookId)}                 >       Add Review  </button>)}
                {selectedBookId === book.bookId && (
                  <div>
                    <input type="text" placeholder="Review content" value={reviewContent} onChange={(e) => setReviewContent(e.target.value)} style={styles.reviewInput} />
                    <input type="number" placeholder="Rating (1-5)" value={rating} onChange={(e) => setRating(parseInt(e.target.value, 10))} style={styles.reviewInput} />
                    <button style={styles.reviewButton} onMouseEnter={(e) => e.target.style.backgroundColor = styles.reviewButtonHover.backgroundColor} onMouseLeave={(e) => e.target.style.backgroundColor = styles.reviewButton.backgroundColor} onClick={() => handleReviewSubmit(book.bookId)}                   >                    Submit Review                   </button>                  </div>)}             </td>
              <td style={styles.td}>
                <button onClick={() => handleEdit(book)}>Edit</button>
                <button onClick={() => handleDelete(book.bookId)}>Delete</button>         </td>            </tr>))}
        </tbody>
      </table>
      <div style={styles.pagination}>
        {[...Array(Math.ceil(books.length / booksPerPage)).keys()].map((number) => (
          <button
            key={number}
            style={styles.pageButton}
            onMouseEnter={(e) => e.target.style.backgroundColor = styles.pageButtonHover.backgroundColor}
            onMouseLeave={(e) => e.target.style.backgroundColor = styles.pageButton.backgroundColor}
            onClick={() => handleClick(number + 1)}         >           {number + 1}         </button>))}
      </div>
    </div>
  );
};

export default BookItem;
