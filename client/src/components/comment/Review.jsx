import { useEffect, useState } from 'react'
import dateFormatter from "../../utils/dateFormatter";
import { Rating } from '@material-tailwind/react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faReply } from '@fortawesome/free-solid-svg-icons';
import useAuth from '../../hooks/useAuth';

const Review = ({ review, handlePostReply }) => {
    const [openReply, setOpenReply] = useState(false);
    const [text, setText] = useState('');
    const [replyFromReview, setReplyFromReview] = useState(false);
    const [activeUsername, setActiveUsername] = useState(null);

    const { auth } = useAuth();

    useEffect(() => {
        if (replyFromReview === false) {
            setText("@" + activeUsername + " ");
        } else {
            setText("");
        }
    }, [openReply]);

    const handleTextareaChange = (event) => {
        const textarea = event.target;
        setText(textarea.value);
        textarea.style.height = 'auto';
        textarea.style.height = `${textarea.scrollHeight}px`;
    };

    const postReply = () => {
        if (!text) return;
        handlePostReply(review.reviewId, text);
        setText("");
        setOpenReply(false);
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
                <div className='w-[85%]'>
                    <div className='flex flex-col w-[100%]'>
                        <p className="text-[0.9rem] font-normal">{review?.userId?.username}</p>
                        <p className="text-[0.75rem] text-gray-400 font-normal">{dateFormatter(review?.reviewDate)}</p>
                    </div>
                    <div className='w-full flex items-center gap-3'>
                        <div className="break-words whitespace-pre-line text-[1rem] font-normal text-gray-600 my-2">
                            {review?.reviewText}
                        </div>

                        <button
                            onClick={() => {
                                setOpenReply(prev => !prev);
                                setReplyFromReview(true);
                                setActiveUsername(review?.userId?.username);
                            }}
                            className='font-bold text-[0.65rem] text-gray-400 flex--center'>
                            Reply
                        </button>
                    </div>


                    <div className='flex flex-col gap-3'>
                        {
                            review?.replies && review?.replies.map((reply, index) => {
                                return (
                                    <>
                                        <div key={index} className='flex gap-2'>
                                            <div className='w-[35px] h-[35px] rounded-full border-black bg-center bg-cover overflow-hidden cursor-pointer'>
                                                <img className="w-[100%] h-[100%] flex--center" src={reply?.userId?.imageUrl} />
                                            </div>
                                            <div className='flex flex-col'>
                                                <p className="text-[0.75rem] font-bold text-gray-600">{reply?.userId?.username}</p>
                                                <p className="text-[0.65rem] text-gray-400 font-normal">{dateFormatter(reply?.createdAt)}</p>
                                                <div className='flex gap-3'>
                                                    <p className="break-words whitespace-pre-line text-[0.85rem] font-normal text-gray-600 my-0.5">
                                                        {reply.responseText}
                                                    </p>
                                                    <button className='flex mt-1.5'>
                                                        <FontAwesomeIcon
                                                            onClick={() => {
                                                                setOpenReply(prev => !prev);
                                                                setReplyFromReview(false);
                                                                setActiveUsername(reply?.userId?.username);
                                                            }}
                                                            className='w-[0.65rem] h-[0.65rem] text-gray-400'
                                                            icon={faReply} />
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </>
                                )
                            })
                        }
                    </div>

                    {openReply &&
                        <div className='flex gap-1 my-2'>
                            <div className='w-[35px] h-[35px] rounded-full border-black bg-center bg-cover overflow-hidden cursor-pointer'>
                                <img className="w-[100%] h-[100%] flex--center" src={auth?.userProfileImage} />
                            </div>
                            <textarea
                                onChange={handleTextareaChange}
                                className="text-sm overflow-y-hidden bg-gray-100 border border-gray-400 leading-normal resize-none py-2 px-2 text-[0.75rem] font-normal placeholder-gray-400 focus:outline-none focus:bg-white"
                                placeholder={`Reply to @${review?.userId?.username}`}
                                value={text}
                            >
                            </textarea>
                            <button
                                className="h-fit border-[2px] border-gray-400 text-gray-600 text-[0.75rem] font-normal px-2 py-1"
                                onClick={postReply}
                            >Send</button>
                            <button
                                className="h-fit border-[2px] border-gray-400 text-gray-600 text-[0.75rem] font-normal px-2 py-1"
                                onClick={() => setOpenReply(false)}
                            >Cancel</button>
                        </div>
                    }

                </div>
            </div>

            <hr className="border-gray-400 my-3 mx-auto" />
        </>
    )
}

export default Review
