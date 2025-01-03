import { atom } from 'recoil';

export const bookState = atom({
  key: 'bookState',
  default: [], // List of books
});
