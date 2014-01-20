function SearchHelper(items)
{
    this.ItemsCollection = items;
}

SearchHelper.prototype.SearchByName = function (name)
{
    var lenght = this.ItemsCollection.lenght;
    for (var i = 0; i < lenght; i++)
    {
        if (this.ItemsCollection[i] == name)
        {
            return true;
        }
    }
    return false;
}
