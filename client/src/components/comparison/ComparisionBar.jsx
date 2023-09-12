import { useNavigate } from "react-router-dom";
import useComparision from "../../hooks/useComparision"

const ComparisionBar = ({ handleClose }) => {
    const { comparedItems, removeItem } = useComparision();
    const navigate = useNavigate();

    return (
        <div className="sticky bottom-0 left-0 right-0 z-20 w-full h-12 bg-white opacity-80">
            <div className="w-full h-full flex--center gap-3">

                <div className="absolute left-2">
                    Category type: {" "}
                    {
                        comparedItems.length > 0 && comparedItems[0].categoryId.categoryName
                    }
                </div>


                {comparedItems.map((item, index) => {
                    return <div
                        key={index}
                        className="relative">
                            <div
                                className='flex--center w-[2.5rem] h-[2.5rem] rounded-full border-black border-[2px] bg-center bg-cover overflow-hidden'>
                                <img className='w-[100%] h-auto' src={item.imageUrl} />
                            </div>
                            <div
                                onClick={() => removeItem(item.productId)}
                                className="absolute text-white font-bold text-[0.75rem] right-0 top-0 w-[1rem] h-[1rem] flex--center border-black bg-red-700 translate-x-1 rounded-full cursor-pointer">
                                -
                            </div>
                        </div>
                })}

                <div className="absolute right-2 flex gap-2">
                    <button
                        onClick={() => {
                            if (comparedItems.length < 2) return;
                            navigate('/product-comparision')
                        }}
                        className="border-[2px] px-2 border-blue-500 text-blue-500">
                        Compare
                    </button>

                    <button
                        onClick={handleClose}
                        className="border-[2px] px-2 border-red-500 text-red-500">
                        Close
                    </button>
                </div>

            </div>
        </div>
    )
}

export default ComparisionBar
