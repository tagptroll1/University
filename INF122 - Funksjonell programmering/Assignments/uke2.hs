
-- B)
False :: Bool
5 + 8 :: Num a => a
(+) 2 :: Num a => a -> a
(+2)  :: Num a => a -> a
(2+)  :: Num a => a -> a
(["foo", "bar"], 'a') :: ([String], Char)
[(True, []), ([False], [['a']])] :: [(Bool, [[Char]])]
\x y -> y !! x :: Int -> [a] -> a
[take, drop, \x y -> [y !! x]] :: [Int -> [a] -> a]

-- C
e1 :: [Bool]
e1 = [False, True, False]

e2 :: Num a => [[a]]
e2 = [[1,2], [3,4]]

e3 :: (Num a) => [(String, a)]
e3 = [("a", 7)]

e4 :: (Num a) => [(Char, a)]
e4 = [('a', 7)] 

e5 :: Num a => a -> a
e5 x = x * 2

e6 :: a -> b -> a
e6 (x, y) = x

e7 :: a -> (a, a)
e7 x = (x, x)
