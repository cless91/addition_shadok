import Test.Hspec
import Shadok

main = hspec $ do
  describe "neutral element" $ do
    it "GA and GA should be GA" $ do
      GA `inventory` GA `shouldBe` (GA, False)
    it "GA and BU should be BU" $ do
      GA `inventory` BU `shouldBe` (BU, False)
    it "ZO and GA should be ZO" $ do
      ZO `inventory` GA `shouldBe` (ZO, False) 
  describe "one word sum without carry" $ do
    it "BU and ZO should be MEU" $ do
      BU `inventory` ZO `shouldBe` (MEU, False)
    it "BU and BU should be ZO" $ do 
      BU `inventory` BU `shouldBe` (ZO, False)
    it "ZO and BU should be MEU" $ do
      ZO `inventory` BU `shouldBe` (MEU, False)
  describe "one word sum with carry" $ do
    it "ZO add ZO should be GA and carry" $ do
      ZO `inventory` ZO `shouldBe` (GA, True) 
    it "BU add MEU should be GA and carry" $ do
      BU `inventory` MEU `shouldBe` (GA, True)
    it "MEU add BU should be GA and carry" $ do
      MEU `inventory` BU `shouldBe` (GA, True)
    it "ZO add MEU should be BU and carry" $ do
      ZO `inventory` MEU `shouldBe` (BU, True)
    it "MEU add ZO should be BU and carry" $ do
      MEU `inventory` ZO `shouldBe` (BU, True)
    it "MEU add MEU should be ZO and carry" $ do
      MEU `inventory` MEU `shouldBe` (ZO, True)

  describe "one word sum no carry" $ do
    it "BU add GA should be BU" $ do
     [BU] `add` [GA] `shouldBe` [BU]
    it "BU add BU should be ZO" $ do
     [BU] `add` [BU] `shouldBe` [ZO]
    it "BU add ZO should be MEU" $ do
     [BU] `add` [ZO] `shouldBe` [MEU]

  describe "two words sum no carry" $ do
    it "BUBU and ZOZO should be MEUMEU" $ do
      [BU, BU] `add` [ZO, ZO] `shouldBe` [MEU, MEU]  
  describe "two words sum no carry" $ do
    it "BUZOBU and ZOBUZO should be MEUMEUMEU" $ do
      [BU, ZO, BU] `add` [ZO, BU, ZO] `shouldBe` [MEU, MEU, MEU]  

  describe "one word sum with carry" $ do
    it "BU add MEU should be BUGA" $ do
     [BU] `add` [MEU] `shouldBe` [BU, GA]

  describe "two word sum with carry" $ do
    it "MEUMEU add ZOZO should be BUZOBU" $ do
     [MEU, MEU] `add` [ZO, ZO] `shouldBe` [BU, ZO, BU]





