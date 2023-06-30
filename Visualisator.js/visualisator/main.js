
import * as THREE from 'three';
import { CustomArrowBufferGeometry } from './CustomArrowBufferGeometry';

function findLocationById(id){
  for(const location of file['locations']){
    if (location['id'] == id){
      return location
    }
  }

}

const scene = new THREE.Scene();

/*window.addEventListener('wheel', ({ deltaY }) => {
  if (deltaY > 0) {
    camera.fov +=10 ;
  }
  else if (deltaY < 0) {
    camera.fov -= 10; 
  
  }
  camera.updateProjectionMatrix;
});*/


const camera = new THREE.PerspectiveCamera(32, window.innerWidth/window.innerHeight,0.1,1000);


const renderer = new THREE.WebGLRenderer({
  canvas: document.querySelector('.webgl'),
});

renderer.setPixelRatio( window.devicePixelRatio);
renderer.setSize( window.innerWidth,window.innerHeight);
camera.position.setZ(30);

renderer.render(scene,camera);


const spherematerial = new THREE.MeshBasicMaterial({color: 0xFF6347, wireframe:true});
const linematerial = new THREE.LineBasicMaterial({color: 0xD3D3D3, wireframe:true});
const trianglematerial = new THREE.MeshBasicMaterial({color: 0xD3D3D3, wireframe:true});


const spheres = [];
const res = await fetch('/test.json');
const file = await res.json();
for( const location of file['locations']){
  const vector = new THREE.Vector3(
    location['coordinates']['xPosition'],
    location['coordinates']['yPosition'],
    location['coordinates']['zPosition']
  );
  const geometry = new THREE.SphereGeometry(0.3,64,64);

  const sphere = new THREE.Mesh(geometry,spherematerial);
  sphere.position.copy(vector);
  scene.add(sphere);
  spheres.push(sphere);
  
}

for(const connection of file['connections']){
    const startid = connection['startLocationId'];
    const finishid =  connection['finishLocationId'];
    
    const startVector = new THREE.Vector3(
      findLocationById(startid)['coordinates']['xPosition'],
      findLocationById(startid)['coordinates']['yPosition'],
      findLocationById(startid)['coordinates']['zPosition']
    );
    const finishVector = new THREE.Vector3(
      findLocationById(finishid)['coordinates']['xPosition'],
      findLocationById(finishid)['coordinates']['yPosition'],
      findLocationById(finishid)['coordinates']['zPosition']
    );
      const lineGeometry = new THREE.BufferGeometry().setFromPoints([
        startVector,
        finishVector,
      ]);

      const line = new THREE.Line(lineGeometry,linematerial);
      scene.add(line);
      
      const triangleGeometry = new THREE.ConeGeometry(0.1,0.2);
      const triangle = new THREE.Mesh(triangleGeometry,trianglematerial);

      const distance = startVector.distanceTo(finishVector);
      const trianglePosition = finishVector.clone().sub(startVector).multiplyScalar(0.05).add(startVector);

      triangle.position.copy(trianglePosition);
      triangle.lookAt(finishVector);
      scene.add(triangle);
  } 

function animate(){
requestAnimationFrame(animate);
renderer.render(scene,camera);

}
animate()