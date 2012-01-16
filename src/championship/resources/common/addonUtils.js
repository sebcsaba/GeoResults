function showOrHideAddonPanel()
{
  fs = window.parent.document.getElementById('frameset');
  a = tokenize(fs.rows,',');
  if (window.parent.addonShown) {
    window.parent.addonPrevSize = a[2];
    a[2] = 0;
  } else {
    a[2] = window.parent.addonPrevSize;
  }
  window.parent.addonShown = !window.parent.addonShown;
  fs.rows = a;
  fa = window.parent.document.getElementById('frame_addon');
  fa.noResize = !window.parent.addonShown;
}

function openAddon(name)
{
  if (window.parent.addonUrl!=name) {
    loadAddon(name);
    if (!window.parent.addonShown) showOrHideAddonPanel();
  } else {
    showOrHideAddonPanel();
  }
}

function loadAddon(name)
{
  if (window.parent.addonUrl!=name) {
    window.parent.addonUrl = name;
    fa = window.parent.document.getElementById('frame_addon');
    fa.contentDocument.location = name;
  }
}
