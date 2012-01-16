function alterTitle(show)
{
  fs = window.parent.document.getElementById('frameset');
  a = tokenize(fs.rows,',');
  if (show) {
    a[0] = window.parent.titlePrevSize;
  } else {
    window.parent.titlePrevSize = a[0];
    a[0] = 0;
  }
  fs.rows = a;
}

function showTitle()
{
  alterTitle(true);
}

function hideTitle()
{
  alterTitle(false);
  if (window.parent.addonShown) showOrHideAddonPanel();
}
