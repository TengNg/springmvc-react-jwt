// src: https://v5.reactrouter.com/web/example/query-parameters

import { useMemo } from "react";
import { useLocation } from "react-router-dom";

export default function useQuery() {
    const { search } = useLocation();
    return useMemo(() => new URLSearchParams(search), [search]);
}
