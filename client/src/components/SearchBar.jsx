import { useState } from 'react';

const SearchBar = ({ handleSearchProduct }) => {
    const [searchOption, setSearchOption] = useState("name");
    const [searchText, setSearchText] = useState("");

    const handleSearch = () => {
        handleSearchProduct(searchOption, searchText);
    }

    return (
        <>
            <div className="flex items-center my-8">
                <select className='h-[2.5rem] outline-0 p-2'
                    value={searchOption}
                    onChange={(e) => setSearchOption(e.target.value)}
                >
                    <option value="name">Name</option>
                    <option value="seller">Seller</option>
                </select>

                <div className="flex border border-purple-200">
                    <input
                        type="text"
                        className="block w-full px-4 py-2 font-bold bg-white border"
                        placeholder="..."
                        value={searchText}
                        onChange={(e) => setSearchText(e.target.value)}
                    />
                    <button onClick={handleSearch} className="px-4 text-white bg-gray-600 border-l">
                        Search
                    </button>
                </div>
            </div>
        </>
    )
}

export default SearchBar
