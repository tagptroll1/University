module Oblig1 where
    -- Thomas André Petersson
    -- tpe044
    
    -- A.a
    fjern :: String -> Int -> String
    fjern string index = front ++ tail back
        where 
            (front, back) = splitAt index string
    
    -- A.b
    fjernc :: String -> Char -> String
    fjernc string chr = [c | c <- string, c /= chr]
    
    -- A.c
    tegnpos :: String -> Char -> [Int]
    tegnpos string chr = [index | (index, c) <- zip [0..] string, c == chr]
    
    -- B.a
    ord :: String -> [String]
    ord s = [reverse x | x <- ord' [] s, x /= ""]
        where
            ord' acc [] = [acc]
            ord' acc (' ':xs) = acc : ord' [] xs
            ord' acc (x:xs) = ord' (x:acc) xs
    
    -- B.b
    tokenize :: String -> String -> String -> [String]
    tokenize str imp rem
        | imp == "" && rem == "" = ord str
        | rem /= "" = ord [ chr | chr <- str, not (chr `elem` rem)]
        | imp /= "" = ord str
        | otherwise = ord str
    
    -- C
    eqli :: Eq t => [t] -> [t] -> Bool
    eqli first second = all (\x -> x `elem` second) first
    -- Return True if all elements in first list overlaps with 2nd list
    
    -- D
    sjekk :: String -> String
    sjekk cs = sjekk' "" cs
        where 
            op ')' = '('
            op ']' = '['
            op '}' = '{'
            op _ = '_'
    
            sjekk' "" "" = "Korrekt"
            sjekk' acc (c': cs')
                | c' `elem` "([{" = sjekk' (c' : acc) cs'
            sjekk' (a: as) (c': cs')
                | c' `elem` ")}]" = if op c' == a 
                    then sjekk' as cs' 
                    else "Feil"
            sjekk' acc (c': cs') = sjekk' acc cs'
            sjekk' _ _ = "Feil"
    
    
    