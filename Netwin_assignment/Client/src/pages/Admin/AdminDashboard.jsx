import React, { useState } from 'react';
import BookForm from '../../components/Books/BookForm';
import BookItem from '../../components/Books/BookItem';

const AdminDashboard = () => {
  const [formData, setFormData] = useState({
    bookId: null,
    title: '',
    authorName: '',
    genreName: '',
    publicationYear: '',
    isAvailable: true,
    quantity: 0,
  });

  return (
    <div>
      <h1>Admin Dashboard</h1>
      <BookForm formData={formData} setFormData={setFormData} />
      <BookItem setFormData={setFormData} />
    </div>
  );
};

export default AdminDashboard;
