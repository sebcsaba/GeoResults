function getEllenorzesTipusValue(name)
{
  if (document.getElementById(name+'_r_1').checked) return document.getElementById(name+'_pv').value;
  if (document.getElementById(name+'_r_2').checked) return 0;
  var s1 = document.getElementById(name+'_s1').value;
  var s2 = document.getElementById(name+'_s2').value;
  return s2*2-2+s1*1;
}

function setEllenorzesTipusDisabled(name,disabled)
{
  document.getElementById(name+'_r_1').disabled = disabled;
  document.getElementById(name+'_r_2').disabled = disabled;
  var etr3 = document.getElementById(name+'_r_3');
  etr3.disabled = disabled;
  document.getElementById(name+'_s1').disabled = disabled || !etr3.checked;
  document.getElementById(name+'_s2').disabled = disabled || !etr3.checked;
}
