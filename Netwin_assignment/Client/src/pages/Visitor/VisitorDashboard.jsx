import React from 'react';
import BookList from '../../components/Books/BookList';
import BookFilters from '../../components/Books/BookFilters';

const VisitorDashboard = () => {
  return (
    <div>
      <h1>Visitor Dashboard</h1>
      <BookFilters />
      <BookList />
    </div>
  );
};

export default VisitorDashboard;
