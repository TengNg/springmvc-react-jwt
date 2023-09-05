import { useRef, useState } from "react";
import useAuth from "../../hooks/useAuth";
import { Rating } from '@material-tailwind/react';

export default function ReviewForm({ handlePostComment }) {
    const [errMsg, setErrMsg] = useState("");
    const [ratingValue, setRatingValue] = useState(3);
    const [text, setText] = useState('');

    const { auth } = useAuth();

    const handleTextareaChange = (event) => {
        const textarea = event.target;
        setText(textarea.value);
        textarea.style.height = 'auto';
        textarea.style.height = `${textarea.scrollHeight}px`;
    };

    const post = (e) => {
        e.preventDefault();

        if (!auth?.accessToken) {
            setErrMsg("Require log in");
            return;
        }

        if (!text) {
            return;
        }

        handlePostComment(text, ratingValue);
        setText("");
    };

    return (
        <>
            <div className="flex flex-col div--style max-w-lg gap-2 h-fit">
                <p className="m-0 p-0 text-[0.75rem] text-red-600">{errMsg}</p>
                <form className="flex w-full max-w-xl bg-white rounded-lg gap-2">
                    <div className="flex flex-wrap">
                        <div className="w-full">
                            <textarea
                                className="text-sm bg-gray-100 rounded border border-gray-400 leading-normal overflow-y-hidden resize-none w-full h-20 py-2 px-3 font-medium placeholder-gray-500 focus:outline-none focus:bg-white"
                                placeholder='Type Your Comment'
                                onChange={handleTextareaChange}
                                required>
                            </textarea>
                        </div>
                    </div>
                    <button
                        type='submit'
                        className="text-sm h-[2rem] font-medium py-1 px-4 border bg-gray-700 text-white cursor-pointer transition-all hover:bg-gray-500"
                        onClick={(e) => post(e)}
                    >
                        Post
                    </button>

                </form>

                <p className="ms-1 text-sm font-normal">Rate this product</p>
                <Rating
                    value={ratingValue}
                    className="w-5 h-5 -rotate-90"
                    onChange={(value) => setRatingValue(value)}
                />

            </div>
        </>
    )

}
