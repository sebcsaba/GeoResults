function popupResults(url)
{
  popup(url,outerWidth,outerHeight,null,null,1,1,1);
}

function popupPanelAnywhere(url,width,height)
{
  popup(url,width,height,null,null,0,1,0);
}

function popupPanel(url,width,height,left,top)
{
  popup(url,width,height,left,top,0,1,0);
}

function popup(url,width,height,left,top,resize,scrollbars,functions)
{
  params = Array();
  if (width!=null) params['width'] = width;
  if (height!=null) params['height'] = height;
  params['resizeable'] = resize;
  params['scrollbars'] = scrollbars;
  params['toolbar'] = functions;
  params['location'] = functions;
  params['directories'] = functions;
  params['status'] = functions;
  params['menubar'] = functions;
  params['copyhistory'] = functions;
  if (left!=null) params['left'] = left;
  if (top!=null) params['top'] = top;
  paramStr = mergeArray(params,'=',',');
  window.parent.popupWindow = window.open(url,'geoResultsPopup',paramStr);
  document.onfocus = closePopup;
}

function closePopup()
{
  if (window.parent.popupWindow!=null) {
    window.parent.popupWindow.close();
    window.parent.popupWindow = null;
  }
  document.onfocus = null;
}

function versenyValasztas(url)
{
  if (url=='') {
    url = '/';
  }
  window.parent.location = url;
}

function closeEredmenyPopup()
{
  if (window.opener!=null) {
    window.opener.closePopup();
  } else {
    window.history.back();
  }
}
