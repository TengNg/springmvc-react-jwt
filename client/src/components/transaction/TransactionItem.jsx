import { useEffect, useState } from 'react';
import dateFormatter from "../../utils/dateFormatter";
import useAuth from '../../hooks/useAuth';
import axios from "../../api/axios";

const TransactionItem = ({ handleShowTransactionDetail, transaction }) => {
    const { transactionDate, amount } = transaction;
    const { auth } = useAuth();

    const showDetail = () => {
        handleShowTransactionDetail(transaction.cartId.cartId);
    };

    return (
        <div onClick={showDetail}
            className='flex flex-row gap-2 div--style div--hover--style w-[80%] h-auto' >
            <div className="flex items-center justify-around p-3 w-[100%]">
                <div className="text-xs">Cart Id: {transaction.cartId.cartId}</div>
                <div className="text-xs">Amount: {amount}</div>
                <div className="text-xs">Transaction Date: {dateFormatter(transactionDate)}</div>
            </div>
        </div>
    )
}

export default TransactionItem
