<!-- <div id="mouse_s">sad</div>
<div id="mouse_p"></div>
<div id="demo">asdasd</div>

<button onclick="getfeatures()">click me</button>
<select id="type">
	<option value="None">Modify</option>
	<option value="LineString">LineString</option>
</select>
<div id="map" style="width: 700px; height: 400px"></div>

<script>
	var style = {

		'LineString' : new ol.style.Style({
			stroke : new ol.style.Stroke({
				color : 'red',
				width : 2
			}),

		}),
	};

	var styleFunction = function(feature) {
		return style[feature.getGeometry().getType()];
	};

	var geojsonObject = {

		'type' : 'Feature',
		'crs' : {
			'type' : 'name'
		},
		'features' : [],
		"geometry" : {
			"type" : "LineString",
			"coordinates" : [ [ 102.0, 0.0 ], [ 103.0, 1.0 ], [ 104.0, 0.0 ],
					[ 105.0, 1.0 ] ],
			"properties" : {
				"prop0" : "value0",
				"prop1" : 0.0
			}
		},
	};
	var features = (new ol.format.GeoJSON()).readFeatures(geojsonObject);
	var vectorSource = new ol.source.Vector({
		features : features,
		format : new ol.format.GeoJSON()
	});

	var vectorLayer = new ol.layer.Vector({
		title : "Vector",
		source : vectorSource,
		style : styleFunction

	});

	var osm = new ol.layer.Tile({
		source : new ol.source.OSM({
			layer : 'sat'
		})
	});

	var map = new ol.Map({
		layers : [ osm, vectorLayer ],
		target : 'map',
		view : new ol.View({
			center : [ 2131312, 6027113 ],
			zoom : 18
		})
	});
	var mousePosition = new ol.control.MousePosition({
		target : 'mouse_p',
		className : 'custom-mouse-position',
		coordinateFormat : ol.coordinate.createStringXY(5),
	});

	var scaleLine = new ol.control.ScaleLine({
		className : 'custom-scale-line',
		target : 'mouse_s',
		units : 'metric',
	});
	map.addControl(scaleLine);
	map.addControl(mousePosition);

	var draw;
	var typeSelect = document.getElementById('type');

	var select = new ol.interaction.Select();
	var modify = new ol.interaction.Modify({
		features : select.getFeatures()
	});

	function addInteraction() {
		var value = typeSelect.value;
		if (value !== 'None') {
			draw = new ol.interaction.Draw({
				source : vectorSource,
				type : (value)
			});
			map.addInteraction(draw);
			vectorSource.addFeatures(draw);
		} else {
			map.addInteraction(select);
			map.addInteraction(modify);
		}

	}

	typeSelect.onchange = function() {
		map.removeInteraction(draw);
		map.removeInteraction(select);
		map.removeInteraction(modify);
		addInteraction();
	};

	addInteraction();

	var writer = new ol.format.GeoJSON();
	var geojsonStr = writer.writeFeatures(vectorSource.getFeatures());

	function getfeatures() {
		document.getElementById("demo").innerHTML = geojsonStr;
	}
</script> -->
<style>
.map {
	height: 400px;
	width: 600px;
}
</style>
<div id="map" class="map"></div>
<div>
	<label>Interaction type: &nbsp;</label> <label>draw</label> <input
		type="radio" id="interaction_type_draw" name="interaction_type"
		value="draw" checked> <label>modify</label> <input
		type="radio" id="interaction_type_modify" name="interaction_type"
		value="modify">
</div>
<div>
	<label>Geometry type</label> <select id="geom_type">
		<option value="Point" selected>Point</option>
		<option value="LineString">LineString</option>
		<option value="Polygon">Polygon</option>
	</select>
</div>
<div>
	<label>Data type</label> <select id="data_type">
		<option value="GeoJSON" selected>GeoJSON</option>
		<option value="KML">KML</option>
		<option value="GPX">GPX</option>
	</select>
</div>
<div id="delete" style="text-decoration: underline; cursor: pointer">
	Delete all features</div>
<label>Data:</label>
<form action="jsonKaydet">
	<textarea id="data" rows="5" style="width: 100%" name="data"></textarea>

	<input type="submit">
</form>


<script type="text/javascript">

	var vector_layer = new ol.layer.Vector({
		name : 'my_vectorlayer',
		source : new ol.source.Vector(),
		style : new ol.style.Style({
			fill : new ol.style.Fill({
				color : 'rgba(255, 255, 255, 0.2)'
			}),
			stroke : new ol.style.Stroke({
				color : '#ffcc33',
				width : 2
			}),
			image : new ol.style.Circle({
				radius : 7,
				fill : new ol.style.Fill({
					color : '#ffcc33'
				})
			})
		})
	});

	// Create a map
	var map = new ol.Map({
		target : 'map',
		layers : [ new ol.layer.Tile({
			source : new ol.source.OSM()
		}), vector_layer ],
		view : new ol.View({
			zoom : 2,
			center : [ 0, 0 ]
		})
	});

	// make interactions global so they can later be removed
	var select_interaction, draw_interaction, modify_interaction;

	// get the interaction type
	var $interaction_type = $('[name="interaction_type"]');
	// rebuild interaction when changed
	$interaction_type.on('click', function(e) {
		// add new interaction
		if (this.value === 'draw') {
			addDrawInteraction();
		} else {
			addModifyInteraction();
		}
	});

	// get geometry type
	var $geom_type = $('#geom_type');
	// rebuild interaction when the geometry type is changed
	$geom_type.on('change', function(e) {
		map.removeInteraction(draw_interaction);
		addDrawInteraction();
	});

	// get data type to save in
	$data_type = $('#data_type');
	// clear map and rebuild interaction when changed
	$data_type.onchange = function() {
		clearMap();
		map.removeInteraction(draw_interaction);
		addDrawInteraction();
	};

	// build up modify interaction
	// needs a select and a modify interaction working together
	function addModifyInteraction() {
		// remove draw interaction
		map.removeInteraction(draw_interaction);
		// create select interaction
		select_interaction = new ol.interaction.Select({
			// make sure only the desired layer can be selected
			layers : function(vector_layer) {
				return vector_layer.get('name') === 'my_vectorlayer';
			}
		});
		map.addInteraction(select_interaction);

		// grab the features from the select interaction to use in the modify interaction
		var selected_features = select_interaction.getFeatures();
		// when a feature is selected...
		selected_features
				.on(
						'add',
						function(event) {
							// grab the feature
							var feature = event.element;
							// ...listen for changes and save them
							feature.on('change', saveData);
							// listen to pressing of delete key, then delete selected features
							$(document)
									.on(
											'keyup',
											function(event) {
												if (event.keyCode == 46) {
													// remove all selected features from select_interaction and my_vectorlayer
													selected_features
															.forEach(function(
																	selected_feature) {
																var selected_feature_id = selected_feature
																		.getId();
																// remove from select_interaction
																selected_features
																		.remove(selected_feature);
																// features aus vectorlayer entfernen
																var vectorlayer_features = vector_layer
																		.getSource()
																		.getFeatures();
																vectorlayer_features
																		.forEach(function(
																				source_feature) {
																			var source_feature_id = source_feature
																					.getId();
																			if (source_feature_id === selected_feature_id) {
																				// remove from my_vectorlayer
																				vector_layer
																						.getSource()
																						.removeFeature(
																								source_feature);
																				// save the changed data
																				saveData();
																			}
																		});
															});
													// remove listener
													$(document).off('keyup');
												}
											});
						});
		// create the modify interaction
		modify_interaction = new ol.interaction.Modify({
			features : selected_features,
			// delete vertices by pressing the SHIFT key
			deleteCondition : function(event) {
				return ol.events.condition.shiftKeyOnly(event)
						&& ol.events.condition.singleClick(event);
			}
		});
		// add it to the map
		map.addInteraction(modify_interaction);
	}

	// creates a draw interaction
	function addDrawInteraction() {
		// remove other interactions
		map.removeInteraction(select_interaction);
		map.removeInteraction(modify_interaction);

		// create the interaction
		draw_interaction = new ol.interaction.Draw({
			source : vector_layer.getSource(),
			type : /** @type {ol.geom.GeometryType} */
			($geom_type.val())
		});
		// add it to the map
		map.addInteraction(draw_interaction);

		// when a new feature has been drawn...
		draw_interaction.on('drawend', function(event) {
			// create a unique id
			// it is later needed to delete features
			var id = uid();
			// give the feature this id
			event.feature.setId(id);
			// save the changed data
			saveData();
		});
	}

	// add the draw interaction when the page is first shown
	addDrawInteraction();

	// shows data in textarea
	// replace this function by what you need
	function saveData() {
		// get the format the user has chosen
		var data_type = $data_type.val(),
		// define a format the data shall be converted to
		format = new ol.format[data_type](),
		// this will be the data in the chosen format
		data;
		try {
			// convert the data of the vector_layer into the chosen format
			data = format.writeFeatures(vector_layer.getSource().getFeatures());
			
			
		} catch (e) {
			// at time of creation there is an error in the GPX format (18.7.2014)
			$('#data').val(e.name + ": " + e.message);
			return;
		}
		if ($data_type.val() === 'GeoJSON') {
			// format is JSON
			
			data2 = data.split('\\');
			
			console.log(JSON.parse(data))
			 
			/* /*  var person = eval('(' +data + ')');
            $("#data") .val(person.features.geometry.coordinate); */
			
			
			
			$('#data').val(JSON.stringify(data));
			
			//$('#data').val(JSON.parse(data2, null, 4));
		} else {
			// format is XML (GPX or KML)
			var serializer = new XMLSerializer();
			$('#data').val(serializer.serializeToString(data));
		}
	}

	// clear map when user clicks on 'Delete all features'
	$("#delete").click(function() {
		clearMap();
	});
	// clears the map and the output of the data
	function clearMap() {
		vector_layer.getSource().clear();
		if (select_interaction) {
			select_interaction.getFeatures().clear();
		}
		$('#data').val('');

	}

	// creates unique id's
	function uid() {
		var id = 0;
		return function() {
			if (arguments[0] === 0) {
				id = 0;
			}
			return id++;
		}
	}
	
	$(document).ready(function(){
	    var textarea = $("#data").val();
	    var linebreak = textarea.split('\\');
	    var length = linebreak.length;
	    var data = [];
	    console.log(length);
	    for ( var i = 0 ; i<length ; i++){
	          
	        data.push({ 'type': i , 'features': linebreak[i] });
	        console.log(data);
	    }
	});
</script>






