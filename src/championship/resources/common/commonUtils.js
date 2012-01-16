function tokenize(str,delim)
{
  result = new Array();
  while ((index=str.indexOf(delim))>-1) {
    result[result.length] = str.substring(0,index);
    str = str.substring(index+1);
  }
  result[result.length] = str;
  return result;
}

function indexOf(array,value)
{
  for (var i=0;i<array.length;++i)
  {
    if (array[i]==value) return i;
  }
  return -1;
}

function mergeArray(arr,conn,sep)
{
  result='';
  for (key in arr) result+=key+conn+arr[key]+sep;
  return result;
}
