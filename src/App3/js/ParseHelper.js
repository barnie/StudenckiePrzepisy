function ParseNameToFullUrl(name)
{
    var p = Windows.Storage.ApplicationData.current.localFolder.path;
    if (p.charAt(p.length - 1) != '\\') p += '\\';
    return p + name;
}

