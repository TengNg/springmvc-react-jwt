import { useRef, useState } from "react";
import useAuth from "../../hooks/useAuth";

export default function ReviewForm({ handlePostComment }) {
    const [errMsg, setErrMsg] = useState("");

    const commentInputRef = useRef(null);

    const { auth } = useAuth();

    const post = (e) => {
        e.preventDefault();

        if (!auth?.accessToken) {
            setErrMsg("Require log in");
            return;
        }

        const reviewText = commentInputRef.current.value;
        handlePostComment(reviewText);

        commentInputRef.current.value = "";
    };

    return (
        <>
            <div className="flex flex-col div--style max-w-lg gap-2 h-fit">
                <div className="flex gap-4">
                    <h2 className="text-sm font-normal">Add a new comment</h2>
                    <p className="m-0 p-0 text-[0.75rem] text-red-600">{errMsg}</p>
                </div>
                <form className="flex w-full max-w-xl bg-white rounded-lg gap-2">
                    <div className="flex flex-wrap">
                        <div className="w-full">
                            <textarea
                                className="text-sm bg-gray-100 rounded border border-gray-400 leading-normal resize-none w-full h-20 py-2 px-3 font-medium placeholder-gray-700 focus:outline-none focus:bg-white"
                                placeholder='Type Your Comment'
                                ref={commentInputRef}
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

            </div>
        </>
    )

}
