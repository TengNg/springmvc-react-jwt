import dateFormatter from "../../utils/dateFormatter";
import useAuth from '../../hooks/useAuth';
import { formatCurrencyVND } from '../../utils/currencyFormatter';

const TransactionItem = ({ handleShowTransactionDetail, handleCancelTransaction, transaction }) => {
    const { transactionDate, amount } = transaction;
    const { auth } = useAuth();

    const showDetail = () => {
        handleShowTransactionDetail(transaction.cartId.cartId);
    };

    const cancelTransaction = () => {
        handleCancelTransaction(transaction.cartId.cartId);
    };

    return (
        <div className='flex flex-row gap-2 div--style div--hover--style w-[80%] h-auto' >
            <div className="flex items-center justify-around p-3 w-[100%]">
                <div className="text-xs">Cart Id: {transaction.cartId.cartId}</div>
                <div className="text-xs">Amount: {formatCurrencyVND(amount)}</div>
                <div className="text-xs">Transaction Date: {dateFormatter(transactionDate)}</div>

                <button
                    onClick={() => showDetail()}
                    className="bg-gray-600 text-white px-4 py-2 text-sm">Details</button>

                <button
                    onClick={() => cancelTransaction()}
                    className="bg-gray-600 text-white px-4 py-2 text-sm">Cancel</button>

            </div>
        </div>
    )
}

export default TransactionItem
