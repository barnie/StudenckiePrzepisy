CREATE TRIGGER dodanie_skladnika AFTER INSERT ON skladniki FOR EACH ROW
BEGIN
    alter table sklad add column || ''+ NEW.nazwa_skladnika integer;
END;


