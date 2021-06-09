package com.example.ar_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.Node;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Sun;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArFragment arFragment;
    private ModelRenderable bedRenderable, chair1Renderable, chair2Renderable, couchRenderable,
                            credenzaRenderable, lampRenderable, ottomanRenderable, pianoRenderable,
                            tableRenderable;

    private Button bedBtn, chair1Btn, chair2Btn,couchBtn, credenzaBtn,
                    lampBtn, ottomanBtn, pianoBtn, tableBtn;

    private Button buttons[];
    private Button removeBtn;

    private int selected = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUI();

        buttons = new Button[]{ bedBtn, chair1Btn, chair2Btn,couchBtn, credenzaBtn,
                lampBtn, ottomanBtn, pianoBtn, tableBtn };

        setClickListener();
        initializeModel();

        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    generateModel(anchorNode, selected);
            }
        });

    }

    private void initializeUI(){

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_fragment);

        bedBtn = (Button) findViewById(R.id.bedBtn);
        chair1Btn = (Button) findViewById(R.id.chair1Btn);
        chair2Btn = (Button) findViewById(R.id.chair2Btn);
        couchBtn = (Button) findViewById(R.id.couchBtn);
        credenzaBtn = (Button) findViewById(R.id.credenzaBtn);
        lampBtn = (Button) findViewById(R.id.lampBtn);
        ottomanBtn = (Button) findViewById(R.id.ottomanBtn);
        pianoBtn = (Button) findViewById(R.id.pianoBtn);
        tableBtn = (Button) findViewById(R.id.tableBtn);


        removeBtn = (Button) findViewById(R.id.removeBtn);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Node> children = new ArrayList<>(arFragment.getArSceneView().getScene().getChildren());
                for (Node node : children) {
                    if(node instanceof AnchorNode) {
                        if(((AnchorNode) node).getAnchor() != null) {
                            ((AnchorNode) node).getAnchor().detach();
                        }
                    }
                    if(!(node instanceof Camera) && !(node instanceof Sun)) {
                        node.setParent(null);
                    }
                }
            }
        });

    }

    private void initializeModel(){
        ModelRenderable.builder()
                .setSource(this, R.raw.bed1)
                .build().thenAccept(renderable -> bedRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unnable to load model", Toast.LENGTH_SHORT).show();
                    return null;
                }
                );

        ModelRenderable.builder()
                .setSource(this, R.raw.chair1)
                .build().thenAccept(renderable -> chair1Renderable = renderable)
                .exceptionally(throwable -> {
                            Toast.makeText(this, "Unnable to load model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this, R.raw.chair2)
                .build().thenAccept(renderable -> chair2Renderable = renderable)
                .exceptionally(throwable -> {
                            Toast.makeText(this, "Unnable to load model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this, R.raw.couch2)
                .build().thenAccept(renderable -> couchRenderable = renderable)
                .exceptionally(throwable -> {
                            Toast.makeText(this, "Unnable to load model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this, R.raw.credenza)
                .build().thenAccept(renderable -> credenzaRenderable = renderable)
                .exceptionally(throwable -> {
                            Toast.makeText(this, "Unnable to load model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this, R.raw.lamp1)
                .build().thenAccept(renderable -> lampRenderable = renderable)
                .exceptionally(throwable -> {
                            Toast.makeText(this, "Unnable to load model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this, R.raw.ottoman)
                .build().thenAccept(renderable -> ottomanRenderable = renderable)
                .exceptionally(throwable -> {
                            Toast.makeText(this, "Unnable to load model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this, R.raw.piano2)
                .build().thenAccept(renderable -> pianoRenderable = renderable)
                .exceptionally(throwable -> {
                            Toast.makeText(this, "Unnable to load model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this, R.raw.table2)
                .build().thenAccept(renderable -> tableRenderable = renderable)
                .exceptionally(throwable -> {
                            Toast.makeText(this, "Unnable to load model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
    }

    private void generateModel(AnchorNode anchorNode, int selected){
        TransformableNode node;
        if(selected == 1){
            node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.getScaleController().setMaxScale(5.0f);
            node.getScaleController().setMinScale(0.1f);
            node.setRenderable(bedRenderable);
            node.select();
        }
        else if(selected == 2){
            node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.getScaleController().setMaxScale(5.0f);
            node.getScaleController().setMinScale(0.1f);
            node.setRenderable(chair1Renderable);
            node.select();
        }
        else if(selected == 3){
            node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.getScaleController().setMaxScale(5.0f);
            node.getScaleController().setMinScale(0.1f);
            node.setRenderable(chair2Renderable);
            node.select();
        }
        else if(selected == 4){
            node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.getScaleController().setMaxScale(5.0f);
            node.getScaleController().setMinScale(0.1f);
            node.setRenderable(couchRenderable);
            node.select();
        }
        else if(selected == 5){
            node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.getScaleController().setMaxScale(5.0f);
            node.getScaleController().setMinScale(0.1f);
            node.setRenderable(credenzaRenderable);
            node.select();
        }
        else if(selected == 6){
            node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.getScaleController().setMaxScale(5.0f);
            node.getScaleController().setMinScale(0.1f);
            node.setRenderable(lampRenderable);
            node.select();
        }
        else if(selected == 7){
            node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.getScaleController().setMaxScale(5.0f);
            node.getScaleController().setMinScale(0.1f);
            node.setRenderable(ottomanRenderable);
            node.select();
        }
        else if(selected == 8){
            node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.getScaleController().setMaxScale(5.0f);
            node.getScaleController().setMinScale(0.1f);
            node.setRenderable(pianoRenderable);
            node.select();
        }
        else if(selected == 9){
            node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.getScaleController().setMaxScale(5.0f);
            node.getScaleController().setMinScale(0.1f);
            node.setRenderable(tableRenderable);
            node.select();
        }
    }

    private void setClickListener(){
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.bedBtn) selected = 1;
        else if(view.getId() == R.id.chair1Btn) selected = 2;
        else if(view.getId() == R.id.chair2Btn) selected = 3;
        else if(view.getId() == R.id.couchBtn) selected = 4;
        else if(view.getId() == R.id.credenzaBtn) selected = 5;
        else if(view.getId() == R.id.lampBtn) selected = 6;
        else if(view.getId() == R.id.ottomanBtn) selected = 7;
        else if(view.getId() == R.id.pianoBtn) selected = 8;
        else if(view.getId() == R.id.tableBtn) selected = 9;

        setButtonColor(view.getId());
    }

    private void setButtonColor(int id){

        for(int i = 0; i < buttons.length; i++){
            if(buttons[i].getId() == id)
                buttons[i].setBackgroundResource(R.drawable.round_btn2);
            else
                buttons[i].setBackgroundResource(R.drawable.round_btn);
        }
    }
}
