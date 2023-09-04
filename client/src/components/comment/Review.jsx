const Review = ({ review }) => {
    return (
        <>
            <div className='w-[100%] flex flex-row gap-4'>
                <div className='w-[40px] h-[40px] rounded-full border-black bg-center bg-cover overflow-hidden cursor-pointer'>
                    <img className="w-[100%] h-[100%] flex--center" src={review?.userId?.imageUrl} />
                </div>
                <div>
                    <div className='flex flex-col'>
                        <p className="text-[0.9rem] font-normal">{review?.userId?.username}</p>
                        <p className="text-[0.75rem] text-gray-400 font-normal">{review?.reviewDate}</p>
                    </div>
                    <p className="text-[0.95rem] font-normal text-gray-600 mt-2">{review?.reviewText}</p>
                </div>
            </div>

            <hr className="border-gray-300 my-3 mx-auto"/>
        </>
    )
}

export default Review
