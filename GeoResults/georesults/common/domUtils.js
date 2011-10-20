function findFirstNodeByName(parent,name)
{
  for (i=0;i<parent.childNodes.length;++i)
  {
    if (parent.childNodes[i].nodeName==name) return parent.childNodes[i];
  }
  return null;
}

function findNthNodeByName(parent,name,n)
{
  for (i=0;i<parent.childNodes.length;++i)
  {
    if (parent.childNodes[i].nodeName==name) {
      --n;
      if (n<=0) return parent.childNodes[i];
    }
  }
  return null;
}

function findParent(elem, parentNodeName)
{
  while (elem!=null && elem.nodeName!=parentNodeName) {
    elem = elem.parentNode;
  }
  return elem;
}

function findNodeIndex(node) {
  p = node.parentNode;
  for ( i=0; i<p.childNodes.length; ++i ) {
    if (p.childNodes[i]==node) return i;
  }
  return -1;
}

