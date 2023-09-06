import { useEffect, useState } from 'react'
import dateFormatter from "../../utils/dateFormatter";
import { Rating } from '@material-tailwind/react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faReply } from '@fortawesome/free-solid-svg-icons';
import useAuth from '../../hooks/useAuth';

const Review = ({ review, handlePostReply }) => {
    const [openReplyForm, setOpenReplyForm] = useState(false);
    const [text, setText] = useState('');
    const [replyFromReview, setReplyFromReview] = useState(false);
    const [activeUsername, setActiveUsername] = useState(null);
    const [hideReplies, setHideReplies] = useState(true);

    const { auth } = useAuth();

    useEffect(() => {
        if (replyFromReview === false) {
            setText("@" + activeUsername + " ");
        } else {
            setText("");
        }
    }, [openReplyForm]);

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
        setOpenReplyForm(false);
        setHideReplies(false);
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
                        <p className="text-[0.9rem] font-bold text-gray-500">{review?.userId?.username}</p>
                        <p className="text-[0.75rem] text-gray-400 font-normal">{dateFormatter(review?.reviewDate)}</p>
                    </div>
                    <div className='w-full flex items-center gap-3'>
                        <div className="break-words whitespace-pre-line text-[1rem] font-normal text-gray-600">
                            {review?.reviewText}
                        </div>

                        <button
                            onClick={() => {
                                setOpenReplyForm(prev => !prev);
                                setReplyFromReview(true);
                                setActiveUsername(review?.userId?.username);
                            }}
                            className='font-bold text-gray-400 text-[0.75rem] h-4 w-8'>
                            Reply
                        </button>
                    </div>

                    {review?.replies
                        && review?.replies.length > 0
                        && <p className='font-bold text-[0.75rem] text-gray-400 mt-2 cursor-pointer'
                            onClick={() => setHideReplies(prev => !prev)} >
                            {hideReplies ? 'Show replies' : 'Hide replies'}
                        </p>
                    }

                    <div className={`flex-col gap-3 mt-2 ${hideReplies ? 'hidden' : 'flex'}`}>
                        {review?.replies && review?.replies.map((reply, index) => {
                            return (
                                <div key={index} className='flex gap-2'>
                                    <div className='w-[40px] h-[40px] rounded-full border-black bg-center bg-cover overflow-hidden cursor-pointer'>
                                        <img className="w-[100%] h-[100%] flex--center" src={reply?.userId?.imageUrl} />
                                    </div>
                                    <div className='flex flex-col'>
                                        <div className='flex flex-row items-center gap-2'>
                                            <p className="text-[0.75rem] font-bold text-gray-500">{reply?.userId?.username}</p>
                                            <p className="text-[0.65rem] text-gray-400 font-normal">{dateFormatter(reply?.createdAt)}</p>
                                        </div>
                                        <div className='flex gap-3'>
                                            <p className="break-words whitespace-pre-line text-[0.85rem] font-normal text-gray-600 my-0.5">
                                                {reply.responseText}
                                            </p>
                                            <button className='flex mt-1.5'>
                                                <FontAwesomeIcon
                                                    onClick={() => {
                                                        setOpenReplyForm(prev => !prev);
                                                        setReplyFromReview(false);
                                                        setActiveUsername(reply?.userId?.username);
                                                    }}
                                                    className='w-[0.65rem] h-[0.65rem] text-gray-400'
                                                    icon={faReply} />
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            )
                        })}
                    </div>

                    {openReplyForm && auth && auth?.accessToken &&
                        <div className='flex gap-1 my-2'>
                            <div className='w-[40px] h-[40px] rounded-full border-black bg-center bg-cover overflow-hidden cursor-pointer'>
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
                                onClick={() => setOpenReplyForm(false)}
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
