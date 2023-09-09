import { useEffect, useState } from 'react'
import axios from "../api/axios";
import useAuth from "../hooks/useAuth"
import dateFormatter from '../utils/dateFormatter';
import { useNavigate } from 'react-router-dom';
import TransactionItem from '../components/transaction/TransactionItem';

const PurchaseHistory = () => {
    const { auth } = useAuth();
    const [transactions, setTransactions] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        if (Object.keys(auth).length > 0) {
            const getTransactions = async () => {
                const response = await axios.get(`/api/purchase-history/${auth?.userId}`);
                setTransactions(response.data.transactions);
            };
            getTransactions().catch(err => {
                console.log(err);
            });
        }
    }, [auth]);

    const handleShowTransactionDetail = (cartId) => {
        navigate(`/purchase-history/transactions/${cartId}`);
    };

    if (!auth.username) {
        return <div className='w-[100%] flex flex--center select-none mx-auto  mt-[4rem]'>
            <button
                className="bg-gray-700 text-white px-5 py-3 font-normal"
                onClick={() => navigate("/shop")}
            >Return to shop</button>
        </div>
    }

    return (
        <section className="relative w-[100%] flex flex-col items-center gap-4">
            <div className="w-[80%] mt-8">
                <div className='flex select-none m-[0_0_2rem_0]'>
                    <h1 className="text-[2rem] text-gray-700 relative text-center font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                    >Purchase History</h1>
                </div>
            </div>

            <div className="flex flex-wrap flex--center flex-col gap-6 w-[80%]">
                {
                    transactions.map((transaction, index) => {
                        return <TransactionItem
                            key={index}
                            transaction={transaction}
                            handleShowTransactionDetail={handleShowTransactionDetail}
                        />

                    })
                }
            </div>

        </section>
    )
}

export default PurchaseHistory
