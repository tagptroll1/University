-- Add x to all elements of [Int]
plu :: [Int] -> Int -> [Int]
plu [] x = []
plu (n:ns) x = n + x : plu ns x

-- Is a string a Palindrome
paliSimple :: String -> Bool
paliSimple str = str == reverse(str)

-- Over complicated version
pali :: String -> Bool
pali [x] = True
pali (s:[css]) = s == css
pali (s:css) = s == head_rcss && pali tail_rcss
    where 
        rcss = reverse(css)
        head_rcss = head rcss
        tail_rcss = tail rcss

-- Reverses the inner lists of a list
reverseInner :: [[a]] -> [[a]]
reverseInner [] =  []
reverseInner (x:xs) = reverse x : reverseInner xs

-- Reverse with list comp
revList :: [[a]] -> [[a]]
revList xs = [reverse a | a <- xs]
