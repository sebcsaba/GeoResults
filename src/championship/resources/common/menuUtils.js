function openMenu(menuTitle)
{
  index = menuTitle.parentNode.id;
  if (index!=window.parent.openedMenu) {
    oldbox = document.getElementById(window.parent.openedMenu);
    oldcnt = findNthNodeByName(oldbox,'DIV',2);
    oldcnt.style.display = 'none';
    cnt = findNthNodeByName(menuTitle.parentNode,'DIV',2);
    cnt.style.display = 'block';
    window.parent.openedMenu = index;
  }
}

function openFirstMenu()
{
  if (window.parent.openedMenu==null) {
    id = 'menuBox_0';
  } else {
    id = window.parent.openedMenu;
  }
  box = document.getElementById(id);
  window.parent.openedMenu = box.id;
  cnt = findNthNodeByName(box,'DIV',2);
  cnt.style.display = 'block';
}
