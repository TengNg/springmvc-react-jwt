const SearchBar = () => {
    return (
        <div className="flex items-center my-8">
            <div className="flex border border-purple-200">
                <input
                    type="text"
                    className="block w-full px-4 py-2 text-purple-700 bg-white border focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40"
                    placeholder="..."
                />
                <button className="px-4 text-white bg-purple-700 border-l">
                    Search
                </button>
            </div>
        </div>
    )
}

export default SearchBar
