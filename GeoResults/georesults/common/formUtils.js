function doFocus(input)
{
  window.parent.activeInputId = input.id;
}

function doUnFocus()
{
  window.parent.activeInputId = null;
}

function doResetForm(formId, newTitle) {
  f = document.getElementById(formId);
  f.reset();
  modImg = document.getElementById(formId+"_mod");
  doUpdateImage(modImg,'notmodified.gif',newTitle);
  return false;
}

function doModifyFormBy(input, newTitle) {
  if (input.form!=null) {
    mod = document.getElementById(input.form.id+"_mod");
    if (mod!=null) doUpdateImage(mod,'modified.gif',newTitle);
  }
}

function doUpdateImage(img, filename, newTitle)
{
  index = img.src.lastIndexOf('/');
  img.src = img.src.substring(0,index)+'/'+filename;
  img.title = newTitle;
}

function numberCheck(input, event) {
  if (event.type=='keypress') {
    cc = event.charCode;
    if (cc==null) cc = event.keyCode;
    if (cc==0) return true;
    if (input.value=='' && cc==45) return true;
    if (cc<48 || cc>57) return false;
  }
  return true;
}

function doChangeSimple(input, event, newTitle)
{
  doModifyFormBy(input,newTitle);
  return true;
}
function doChangeText(input, event, newTitle)
{
  doModifyFormBy(input,newTitle);
  dict = document.getElementById(input.id+'_dict');
  if (dict!=null) {
    dict.value = '';
    input.style.backgroundColor = 'white';
  }
  return true;
}
function doChangeInteger(input, event, newTitle)
{
  if (numberCheck(input,event)) {
    doModifyFormBy(input,newTitle);
    return true;
  } else return false;
}

function doChangeFelirat(input, event)
{
  if (event.type=='change') {
    input.value = input.value.toUpperCase();
  }
  return true;
}

function doEnableSimple(cbox, newTitle)
{
  name = cbox.name.substring(0,(cbox.name.lastIndexOf('_')));
  doEnable(cbox,name);
  doModifyFormBy(cbox,newTitle);
}
function doEnableDate(cbox, newTitle)
{
  name = cbox.name.substring(0,(cbox.name.lastIndexOf('_')));
  doEnable(cbox,name+'_year');
  doEnable(cbox,name+'_month');
  doEnable(cbox,name+'_day');
  doModifyFormBy(cbox,newTitle);
}
function doEnableTime(cbox, newTitle)
{
  name = cbox.name.substring(0,(cbox.name.lastIndexOf('_')));
  doEnable(cbox,name+'_hour');
  doEnable(cbox,name+'_min');
  doModifyFormBy(cbox,newTitle);
}
function doEnable(cbox, name)
{
  field = cbox.form.elements[name];
  field.disabled = !cbox.checked;
}
