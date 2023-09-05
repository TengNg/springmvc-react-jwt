const Reply = () => {
    return (
        <>
            <div className='flex gap-1'>
                <div className='w-[30px] h-[30px] rounded-full border-black bg-center bg-cover overflow-hidden cursor-pointer'>
                    <img className="w-[100%] h-[100%] flex--center" src={review?.userId?.imageUrl} />
                </div>
                <textarea
                    onChange={handleTextareaChange}
                    className="text-sm overflow-y-hidden bg-gray-100 border border-gray-400 leading-normal resize-none py-2 px-3 text-[0.75rem] font-normal placeholder-gray-400 focus:outline-none focus:bg-white"
                    value={text}
                >
                </textarea>
                {/* <button */}
                {/*     className="h-fit bg-gray-600 text-white text-[0.75rem] font-normal px-2 py-1" */}
                {/*     onClick={postReply} */}
                {/* >Send</button> */}
            </div>
            <hr className="border-gray-400 my-3 mx-auto" />
        </>
    )
}

export default Reply
