
import * as THREE from 'three';

const scene = new THREE.Scene();


const camera = new THREE.PerspectiveCamera(75, window.innerWidth/window.innerHeight,0.1,1000);

const renderer = new THREE.WebGLRenderer({
  canvas: document.querySelector('.webgl'),
});

renderer.setPixelRatio( window.devicePixelRatio);
renderer.setSize( window.innerWidth,window.innerHeight);
camera.position.setZ(30);

renderer.render(scene,camera);

const geometry = new THREE.SphereGeometry(10,64,64);
const material = new THREE.MeshBasicMaterial({color: 0xFF6347, wireframe:true});
const sphere = new THREE.Mesh(geometry,material);

scene.add(sphere);

function animate(){
requestAnimationFrame(animate);
renderer.render(scene,camera);

//sphere.rotation.x += 0.01;

}
animate()