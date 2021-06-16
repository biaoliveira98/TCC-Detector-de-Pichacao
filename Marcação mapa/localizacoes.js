//apenas um exemplo

var locations = [
  ['Foto 1', -23.0047130505, -47.1487406776],
  ['Foto 2', -22.809731877, -47.0397951454],
  ['Foto 3', -22.900833, -47.057222],
  ['Foto 4', -22.900833, -47.057222],
  ['Foto 5', -22.900833, -47.057222]
];
 
for (i = 0; i < locations.length; i++) {  
  marker = new google.maps.Marker({
position: new google.maps.LatLng(locations[i][1], locations[i][2]),
    title: locations[i][0],
    map: map
  });
}

