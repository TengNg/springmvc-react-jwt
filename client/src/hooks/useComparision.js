import { useContext } from "react";
import ComparsionContext from "../context/ComparisionContextProvider";

const useComparision = () => {
    return useContext(ComparsionContext);
}

export default useComparision;
