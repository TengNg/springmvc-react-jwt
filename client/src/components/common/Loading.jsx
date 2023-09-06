const Loading = ({ loading }) => {
    return (
        <>
            {
                loading
                && <div className="absolute w-full h-full bg-white opacity-70 z-50 font-bold text-2xl flex--center">
                    Loading...
                </div>
            }
        </>
    )
}

export default Loading
