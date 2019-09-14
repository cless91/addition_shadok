module Shadok where

data ShadokVerb = GA  | BU | ZO | MEU 
  deriving (Show, Eq, Enum)

type SumInfo = (ShadokVerb, Bool)

inventory:: ShadokVerb->ShadokVerb->SumInfo
inventory y GA    = (y, False)
inventory GA y    = (y, False)
inventory BU MEU  = (GA, True)
inventory MEU BU  = (GA, True)
inventory BU x    = (succ x, False)
inventory x BU    = (succ x, False)
inventory ZO ZO   = (GA, True)
inventory MEU ZO  = (BU, True)
inventory ZO MEU  = (BU, True)
inventory MEU MEU = (ZO, True)

add::[ShadokVerb] -> [ShadokVerb] -> [ShadokVerb]
add [x] [y] = 
      addKeep (inventory x y) 
  ++ [fst (inventory x y)]

add (x:xs) (y:ys) = [x] `add` [y] ++ xs `add` ys

addKeep (_, False) = []
addKeep (_, True) = [BU]
