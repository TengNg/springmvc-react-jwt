import React, { useState } from 'react';

const Pagination = ({ totalItems, itemsPerPage, currentPage, handlePaginate }) => {
    const pageNumbers = [];

    for (let i = 1; i <= Math.ceil(totalItems / itemsPerPage); i++) {
        pageNumbers.push(i);
    }

    return (
        <nav className='div--style mt-8 mx-auto'>
            <ul className='flex gap-6 px-4'>
                {pageNumbers.map(number => (
                    <li key={number}>
                        <a
                            className={`font-bold cursor-pointer ${currentPage === number ? 'text-blue-600' : 'text-gray-600'}`}
                            onClick={() => {
                                handlePaginate(number)
                            }}>
                            {number}
                        </a>
                    </li>
                ))}
            </ul>
        </nav>
    );
};

export default Pagination;
