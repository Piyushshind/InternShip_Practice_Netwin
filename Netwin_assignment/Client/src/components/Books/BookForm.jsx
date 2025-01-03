import React from "react";
import { useRecoilState } from "recoil";
import { bookState } from "../../recoil/bookState";
import api from "../../services/api";

const BookForm = ({ formData, setFormData }) => {
  const [books, setBooks] = useRecoilState(bookState);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: name === "isAvailable" ? e.target.checked : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const publicationYearRegex = /^([1-9]\d{2}|[1-9]\d{3}|9999)$/;
    if (!publicationYearRegex.test(formData.publicationYear)) {
      alert("Publication Year must be between 999 and 9999.");
      return;
    }

    if (formData.quantityAvailable < 0) {
      alert("Quantity Available should not be negative.");
      return;
    }

    try {
      const bookData = {
        title: formData.title,
        publicationYear: formData.publicationYear,
        authorName: formData.authorName,
        genreName: formData.genreName,
        quantityAvailable: formData.quantityAvailable,
      };

      if (formData.bookId) {
        const response = await api.put(`/books/${formData.bookId}`, bookData);
        setBooks((prevBooks) =>
          prevBooks.map((book) =>
            book.bookId === response.data.bookId ? response.data : book
          )
        );
        alert("Book updated successfully");
      } else {
        const response = await api.post("/books", bookData);
        setBooks((prevBooks) => [...prevBooks, response.data]);
        alert("Book added successfully");
      }
      resetForm();
    } catch (error) {
      console.error("Error submitting book:", error);
      alert("Failed to submit book. Please try again.");
    }
  };

  const resetForm = () => {
    setFormData({
      bookId: null,
      title: "",
      authorName: "",
      genreName: "",
      publicationYear: 0,
      quantityAvailable: 0,
    });
  };

  const styles = {
    form: {
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      backgroundColor: '#f9f9f9',
      padding: '20px',
      borderRadius: '8px',
      boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)'
    },
    formControl: {
      margin: '10px 0',
      width: '100%',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'space-between'
    },
    label: {
      fontWeight: 'bold',
      flex: '0 0 150px',
      marginRight: '10px'
    },
    input: {
      flex: '1 0',
      padding: '10px',
      margin: '5px 0',
      boxSizing: 'border-box',
      borderRadius: '4px',
      border: '1px solid #ddd'
    },
    buttonContainer: {
      display: 'flex',
      justifyContent: 'center',
      margin: '20px 0'
    },
    button: {
      padding: '10px 20px',
      margin: '10px',
      border: 'none',
      cursor: 'pointer',
      backgroundColor: '#007bff',
      color: '#fff',
      borderRadius: '4px',
      transition: 'background-color 0.3s'
    },
    buttonHover: {
      backgroundColor: '#0056b3'
    }
  };

  return (
    <form onSubmit={handleSubmit} style={styles.form} className="book-form">
      <h2>{formData.bookId ? "Edit Book" : "Add Book"}</h2>
      <div style={styles.formControl}>
        <label style={styles.label}>Title</label>
        <input
          type="text"
          name="title"
          value={formData.title}
          onChange={handleChange}
          placeholder="Title"
          required
          style={styles.input}
        />
      </div>
      <div style={styles.formControl}>
        <label style={styles.label}>Author Name</label>
        <input
          type="text"
          name="authorName"
          value={formData.authorName}
          onChange={handleChange}
          placeholder="Author Name"
          required
          style={styles.input}
        />
      </div>
      <div style={styles.formControl}>
        <label style={styles.label}>Genre</label>
        <input
          type="text"
          name="genreName"
          value={formData.genreName}
          onChange={handleChange}
          placeholder="Genre"
          required
          style={styles.input}
        />
      </div>
      <div style={styles.formControl}>
        <label style={styles.label}>Publication Year</label>
        <input
          type="number"
          name="publicationYear"
          value={formData.publicationYear}
          onChange={handleChange}
          placeholder="Publication Year"
          required
          style={styles.input}
        />
      </div>
      <div style={styles.formControl}>
        <label style={styles.label}>Quantity Available</label>
        <input
          type="number"
          name="quantityAvailable"
          value={formData.quantityAvailable}
          onChange={handleChange}
          placeholder="Quantity Available"
          required
          style={styles.input}
        />
      </div>
      <div style={styles.buttonContainer}>
        <button
          type="submit"
          style={styles.button}
          onMouseEnter={(e) => (e.target.style.backgroundColor = styles.buttonHover.backgroundColor)}
          onMouseLeave={(e) => (e.target.style.backgroundColor = styles.button.backgroundColor)}
        >
          {formData.bookId ? "Update" : "Add"} Book
        </button>
        {formData.bookId && (
          <button type="button" onClick={resetForm} style={styles.button}>
            Cancel Edit
          </button>
        )}
      </div>
    </form>
  );
};

export default BookForm;
