import { useNavigate } from 'react-router-dom';

const Home = () => {
    const navigate = useNavigate();

    return (
        <div className='flex--center flex-col gap-2 mt-7'>
            <div className='w-[100%] flex select-none ps-[8rem]'>
                <h1 className="text-[2.5rem] text-gray-700 relative font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                >About</h1>
            </div>
            <section className="div--style mx-[7rem] h-[auto] p-9 text-justify flex flex-col justify-center gap-4 my-[2rem]">
                <p>
                    Lorem ipsum dolor sit amet, officia excepteur ex fugiat reprehenderit enim labore culpa sint ad nisi Lorem pariatur mollit ex esse exercitation amet. Nisi anim cupidatat excepteur officia. Reprehenderit nostrud nostrud ipsum Lorem est aliquip amet voluptate voluptate dolor minim nulla est proident. Nostrud officia pariatur ut officia. Sit irure elit esse ea nulla sunt ex occaecat reprehenderit commodo officia dolor Lorem duis laboris cupidatat officia voluptate. Culpa proident adipisicing id nulla nisi laboris ex in Lorem sunt duis officia eiusmod. Aliqua reprehenderit commodo ex non excepteur duis sunt velit enim. Voluptate laboris sint cupidatat ullamco ut ea consectetur et est culpa et culpa duis.
                </p>
                <p className='text-sm font-normal'>
                    Lorem ipsum dolor sit amet, qui minim labore adipisicing minim sint cillum sint consectetur cupidatat.
                </p>
            </section>
            <div>
                <div className='w-[10rem] h-[5rem] mt-4'>
                    <button
                        onClick={() => navigate("/shop")}
                        className="button--style button--hover">Explore</button>
                </div>
            </div>
        </div>
    )
}

export default Home
