import { useState } from 'react'
import dateFormatter from "../../utils/dateFormatter";
import { Rating } from '@material-tailwind/react';

const Review = ({ review }) => {
    const [openReply, setOpenReply] = useState(false);
    const [text, setText] = useState('');

    const handleTextareaChange = (event) => {
        const textarea = event.target;
        setText(textarea.value);
        textarea.style.height = 'auto';
        textarea.style.height = `${textarea.scrollHeight}px`;
    };

    return (
        <>
            <div className='relative w-[100%] flex flex-row gap-4'>
                <Rating
                    value={review?.rating}
                    className="absolute top-0 right-[3.25rem] w-3 h-3 -rotate-90"
                    readonly
                />

                <div className='w-[50px] h-[50px] rounded-full border-black bg-center bg-cover overflow-hidden cursor-pointer'>
                    <img className="w-[100%] h-[100%] flex--center" src={review?.userId?.imageUrl} />
                </div>
                <div>
                    <div className='flex flex-col w-[100%]'>
                        <p className="text-[0.9rem] font-normal">{review?.userId?.username}</p>
                        <p className="text-[0.75rem] text-gray-400 font-normal">{dateFormatter(review?.reviewDate)}</p>
                    </div>
                    <div className='w-full'>
                        <p className="w-[90%] break-words whitespace-pre-line text-[1rem] font-normal text-gray-600 my-2">
                            {review?.reviewText}
                        </p>
                    </div>
                    <button
                        onClick={() => setOpenReply(prev => !prev)}
                        className="text-[0.75rem] text-gray-600"
                    >Reply</button>

                    { openReply &&
                        <div className='flex gap-1'>
                            <div className='w-[30px] h-[30px] rounded-full border-black bg-center bg-cover overflow-hidden cursor-pointer'>
                                <img className="w-[100%] h-[100%] flex--center" src={review?.userId?.imageUrl} />
                            </div>
                            <textarea
                                onChange={handleTextareaChange}
                                className="text-sm overflow-y-hidden bg-gray-100 rounded border border-gray-400 leading-normal resize-none py-2 px-3 text-[0.75rem] font-normal placeholder-gray-400 focus:outline-none focus:bg-white"
                                placeholder={`Reply to @${review?.userId?.username}`}
                            >
                            </textarea>
                            <button className="h-fit bg-gray-600 text-white text-[0.75rem] font-normal px-2 py-1">Send</button>
                        </div>
                    }

                </div>
            </div>

            <hr className="border-gray-400 my-3 mx-auto" />
        </>
    )
}

export default Review
