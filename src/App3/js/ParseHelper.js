function ParseHelper() {
}

ParseHelper.prototype.ParseNameToFullUrl = function(name)
{
    return Windows.Storage.StorageFolder.Path + name;
}

