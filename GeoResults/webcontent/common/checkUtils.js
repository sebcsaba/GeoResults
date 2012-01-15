function checkValueTextBg(input,values)
{
  if (indexOf(values,input.value)==-1)
  {
    input.style.background = 'red';
  } else {
    input.style.background = '';
  }
}
